package clinicaveterinaria;

public class ClinicaVeterinariaManualTest {

    public static void main(String[] args) {
        devePermitirMudancaValidaDeSituacao();
        deveBloquearMudancaInvalidaDeSituacao();
        deveEnviarAvisoAutomaticoAoTutor();
        deveCalcularValorFinalComMaisDeUmaRegraAplicada();
        System.out.println("Todos os testes manuais passaram com sucesso!");
    }

    private static void devePermitirMudancaValidaDeSituacao() {
        Atendimento atendimento = criarAtendimentoSimples();
        atendimento.iniciar();
        assertEquals("EmAtendimento", atendimento.getSituacaoAtual());
        System.out.println("OK - mudança válida de Agendado para EmAtendimento");
    }

    private static void deveBloquearMudancaInvalidaDeSituacao() {
        Atendimento atendimento = criarAtendimentoSimples();
        atendimento.iniciar();
        atendimento.finalizar();

        try {
            atendimento.cancelar();
            throw new AssertionError("Era esperado erro ao cancelar atendimento finalizado.");
        } catch (IllegalStateException e) {
            assertEquals("Finalizado", atendimento.getSituacaoAtual());
            System.out.println("OK - tentativa inválida de cancelar atendimento finalizado");
        }
    }

    private static void deveEnviarAvisoAutomaticoAoTutor() {
        Tutor tutor = new Tutor("Ana", "(32) 99999-0000");
        Animal animal = new Animal("Mel", "Cachorro", 4, false);
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);
        Atendimento atendimento = new Atendimento("ATD-002", tutor, animal, servico);
        atendimento.adicionarObservador(tutor);

        atendimento.iniciar();

        assertEquals("Tutor Ana avisado: atendimento do animal Mel foi iniciado.", tutor.getUltimaMensagemRecebida());
        System.out.println("OK - aviso automático enviado ao tutor");
    }

    private static void deveCalcularValorFinalComMaisDeUmaRegraAplicada() {
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);
        servico = new DescontoAnimalAdotado(servico);
        servico = new TaxaAtendimentoDomiciliar(servico);
        servico = new BanhoPosConsulta(servico);

        assertEquals(310.00, servico.getValor());
        System.out.println("OK - valor final com desconto, taxa domiciliar e banho pós-consulta = 310.0");
    }

    private static Atendimento criarAtendimentoSimples() {
        Tutor tutor = new Tutor("Ana", "(32) 99999-0000");
        Animal animal = new Animal("Mel", "Cachorro", 4, false);
        ServicoVeterinario servico = new ServicoBase("Consulta clínica", 200.00);
        return new Atendimento("ATD-001", tutor, animal, servico);
    }

    private static void assertEquals(String esperado, String obtido) {
        if (!esperado.equals(obtido)) {
            throw new AssertionError("Esperado: " + esperado + " | Obtido: " + obtido);
        }
    }

    private static void assertEquals(double esperado, double obtido) {
        if (Math.abs(esperado - obtido) > 0.0001) {
            throw new AssertionError("Esperado: " + esperado + " | Obtido: " + obtido);
        }
    }
}
