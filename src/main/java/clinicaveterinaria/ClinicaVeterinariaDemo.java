package clinicaveterinaria;

public class ClinicaVeterinariaDemo {

    public static void main(String[] args) {
        // 1. Instanciando Entidades Base e Observers
        Tutor tutor = new Tutor("João", "(32) 98888-1111");
        Animal animal = new Animal("Rex", "Cachorro", 2, true);
        Veterinario veterinario = new Veterinario("Dra. Amanda", "CRMV-9876");
        Recepcao recepcao = new Recepcao("Carlos");

        // Novos Observers
        SetorFaturamento faturamento = new SetorFaturamento();
        ControleMedicacao farmacia = new ControleMedicacao();
        AuditoriaLog auditoria = new AuditoriaLog();

        Atendimento atendimento = new AtendimentoBuilder("ATD-999")
                .comTutor(tutor)
                .comAnimal(animal)
                .comServicoBase(new ServicoBase("Cirurgia Ortopédica", 1500.00))
                .adicionarRegraDePreco(DescontoConvenioONG::new)
                .adicionarRegraDePreco(BanhoPosConsulta::new)

                // Encadeamos todos os observadores no Builder
                .adicionarObservador(tutor)
                .adicionarObservador(veterinario)
                .adicionarObservador(recepcao)
                .adicionarObservador(faturamento)

                // Finalizamos com o build() por último!
                .build();

        // 3. Executando o Fluxo de Negócio
        System.out.println("--- INICIANDO FLUXO ---");
        atendimento.iniciar();
        System.out.println(tutor.getUltimaMensagemRecebida());
        System.out.println(farmacia.getStatusFarmacia()); // Farmácia acionada para medicação

        atendimento.finalizar();
        System.out.println(recepcao.getUltimaMensagemRecebida());

        // 4. Exibindo os Resultados Computados
        System.out.println("\n--- DADOS DO FATURAMENTO ---");
        System.out.println("Serviços prestados: " + atendimento.getDescricaoServico());
        System.out.println("Valor final a ser cobrado: R$ " + String.format("%.2f", atendimento.calcularValorFinal()));
        System.out.println(faturamento.getUltimaNotaEmitida());

        // 5. Exibindo o Log de Rastreabilidade
        System.out.println("");
        auditoria.imprimirLogs();
    }
}