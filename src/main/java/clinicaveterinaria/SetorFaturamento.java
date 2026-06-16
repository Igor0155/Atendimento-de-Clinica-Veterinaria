package clinicaveterinaria;

public class SetorFaturamento implements ObservadorAtendimento {

    private double totalFaturadoDia;
    private String ultimaNotaEmitida;

    public SetorFaturamento() {
        this.totalFaturadoDia = 0.0;
    }

    public double getTotalFaturadoDia() {
        return totalFaturadoDia;
    }

    public String getUltimaNotaEmitida() {
        return ultimaNotaEmitida;
    }

    @Override
    public void atualizar(Atendimento atendimento, EventoAtendimento evento) {
        // O faturamento só se importa com atendimentos finalizados
        if (evento == EventoAtendimento.FINALIZADO) {
            double valorCobrado = atendimento.calcularValorFinal();
            this.totalFaturadoDia += valorCobrado;

            this.ultimaNotaEmitida = "NF emitida para o atendimento " + atendimento.getCodigo()
                    + " | Tutor: " + atendimento.getTutor().getNome()
                    + " | Valor: R$ " + String.format("%.2f", valorCobrado);
        }
    }
}