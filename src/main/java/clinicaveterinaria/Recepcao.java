package clinicaveterinaria;

public class Recepcao implements ObservadorAtendimento {

    private String nomeResponsavel;
    private String ultimaMensagemRecebida;

    public Recepcao(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getNomeResponsavel() { return nomeResponsavel; }
    public String getUltimaMensagemRecebida() { return ultimaMensagemRecebida; }

    @Override
    public void atualizar(Atendimento atendimento, EventoAtendimento evento) {
        if (evento == EventoAtendimento.FINALIZADO) {
            this.ultimaMensagemRecebida = "Recepção avisada: atendimento "
                    + atendimento.getCodigo() + " foi finalizado.";
        }
    }
}
