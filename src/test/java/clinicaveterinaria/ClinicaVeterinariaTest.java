package clinicaveterinaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ClinicaVeterinariaTest {

    @Test
    public void devePermitirMudancaValidaDeSituacao() {
        Atendimento atendimento = criarAtendimentoSimples();

        atendimento.iniciar();

        assertEquals("EmAtendimento", atendimento.getSituacaoAtual());
    }

    @Test
    public void deveBloquearMudancaInvalidaDeSituacao() {
        Atendimento atendimento = criarAtendimentoSimples();
        atendimento.iniciar();
        atendimento.finalizar();

        assertThrows(IllegalStateException.class, atendimento::cancelar);
        assertEquals("Finalizado", atendimento.getSituacaoAtual());
    }

    @Test
    public void deveEnviarAvisoAutomaticoAoTutorQuandoAtendimentoForIniciado() {
        Tutor tutor = new Tutor("Ana", "(32) 99999-0000");
        Animal animal = new Animal("Mel", "Cachorro", 4, false);
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);

        Atendimento atendimento = new AtendimentoBuilder("ATD-002")
                .comTutor(tutor)
                .comAnimal(animal)
                .comServicoBase(servico)
                .adicionarObservador(tutor)
                .build();

        atendimento.iniciar();

        assertEquals(
                "Tutor Ana avisado: atendimento do animal Mel foi iniciado.",
                tutor.getUltimaMensagemRecebida());
    }

    @Test
    public void deveEnviarAvisoAutomaticoAoVeterinarioQuandoAtendimentoForCancelado() {
        Veterinario veterinario = new Veterinario("Dr. Carlos", "CRMV-1234");
        Tutor tutor = new Tutor("Ana", "(32) 99999-0000");
        Animal animal = new Animal("Mel", "Cachorro", 4, false);
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);

        Atendimento atendimento = new AtendimentoBuilder("ATD-001")
                .comTutor(tutor)
                .comAnimal(animal)
                .comServicoBase(servico)
                .adicionarObservador(veterinario)
                .build();

        atendimento.cancelar();

        assertEquals(
                "Veterinário Dr. Carlos avisado: atendimento ATD-001 foi cancelado.",
                veterinario.getUltimaMensagemRecebida());
    }

    @Test
    public void deveEnviarAvisoAutomaticoARecepcaoQuandoAtendimentoForFinalizado() {
        Recepcao recepcao = new Recepcao("Julia");
        Tutor tutor = new Tutor("Ana", "(32) 99999-0000");
        Animal animal = new Animal("Mel", "Cachorro", 4, false);
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);

        Atendimento atendimento = new AtendimentoBuilder("ATD-001")
                .comTutor(tutor)
                .comAnimal(animal)
                .comServicoBase(servico)
                .adicionarObservador(recepcao)
                .build();

        atendimento.iniciar();
        atendimento.finalizar();

        assertEquals(
                "Recepção avisada: atendimento ATD-001 foi finalizado.",
                recepcao.getUltimaMensagemRecebida());
    }

    @Test
    public void deveCalcularValorFinalComMaisDeUmaRegraAplicada() {
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);
        servico = new DescontoAnimalAdotado(servico);
        servico = new TaxaAtendimentoDomiciliar(servico);
        servico = new BanhoPosConsulta(servico);

        assertEquals(310.00, servico.getValor());
        assertEquals(
                "Consulta clínica + desconto animal adotado + atendimento domiciliar + banho pós-consulta",
                servico.getDescricao());
    }

    // --- NOVOS TESTES ADICIONADOS ---

    @Test
    public void deveLancarExcecaoAoTentarConstruirAtendimentoSemDadosObrigatorios() {
        AtendimentoBuilder builderIncompleto = new AtendimentoBuilder("ATD-ERRO");

        assertThrows(IllegalStateException.class, builderIncompleto::build);
    }

    @Test
    public void deveCalcularValorComNovosDecoratorsONGNoturno() {
        Atendimento atendimento = new AtendimentoBuilder("ATD-TESTE-PRECO")
                .comTutor(new Tutor("ONG", "0000"))
                .comAnimal(new Animal("Bidu", "Cachorro", 2, true))
                .comServicoBase(new ServicoBase("Cirurgia", 1000.00))
                .adicionarRegraDePreco(DescontoConvenioONG::new) // 1000 * 0.7 = 700.0
                .adicionarRegraDePreco(TaxaAtendimentoDomiciliar::new) // 700 + 50 = 750.0
                .adicionarRegraDePreco(AdicionalNoturno::new) // 750 * 1.2 = 900.0
                .build();

        assertEquals(900.00, atendimento.calcularValorFinal());
        assertEquals(
                "Cirurgia + convênio ONG parceira + atendimento domiciliar + adicional noturno (20%)",
                atendimento.getDescricaoServico());
    }

    @Test
    public void deveNotificarNovosObserversCorretamenteNoFluxoCompleto() {
        ControleMedicacao farmacia = new ControleMedicacao();
        SetorFaturamento faturamento = new SetorFaturamento();
        AuditoriaLog auditoria = new AuditoriaLog();

        Atendimento atendimento = new AtendimentoBuilder("ATD-FLUXO")
                .comTutor(new Tutor("Marcos", "1111"))
                .comAnimal(new Animal("Nina", "Gato", 1, false))
                .comServicoBase(new ServicoBase("Consulta", 100.00))
                .adicionarObservador(farmacia)
                .adicionarObservador(faturamento)
                .adicionarObservador(auditoria)
                .build();

        // Ação 1: Iniciar
        atendimento.iniciar();
        assertEquals("Farmácia notificada: Preparar kit de triagem e controle de medicação para o animal Nina",
                farmacia.getStatusFarmacia());
        assertEquals(1, auditoria.getHistoricoLogs().size());
        assertTrue(auditoria.getHistoricoLogs().get(0).contains("INICIADO"));

        // Ação 2: Finalizar
        atendimento.finalizar();
        assertEquals(100.00, faturamento.getTotalFaturadoDia());
        assertEquals("NF emitida para o atendimento ATD-FLUXO | Tutor: Marcos | Valor: R$ 100,00",
                faturamento.getUltimaNotaEmitida().replace(".", ","));
        assertEquals(2, auditoria.getHistoricoLogs().size());
        assertTrue(auditoria.getHistoricoLogs().get(1).contains("FINALIZADO"));
    }

    // Método auxiliar atualizado para usar o Builder
    private Atendimento criarAtendimentoSimples() {
        Tutor tutor = new Tutor("Ana", "(32) 99999-0000");
        Animal animal = new Animal("Mel", "Cachorro", 4, false);
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);

        return new AtendimentoBuilder("ATD-001")
                .comTutor(tutor)
                .comAnimal(animal)
                .comServicoBase(servico)
                .build();
    }
}