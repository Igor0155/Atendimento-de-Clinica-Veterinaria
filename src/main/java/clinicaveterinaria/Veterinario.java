package clinicaveterinaria;

public class Veterinario implements ObservadorAtendimento {

    private String nome;
    private String crmv;
    private String ultimaMensagemRecebida;

    public Veterinario(String nome, String crmv) {
        this.nome = nome;
        this.crmv = crmv;
    }

    public String getNome() { return nome; }
    public String getCrmv() { return crmv; }
    public String getUltimaMensagemRecebida() { return ultimaMensagemRecebida; }

    @Override
    public void atualizar(Atendimento atendimento, EventoAtendimento evento) {
        if (evento == EventoAtendimento.CANCELADO) {
            this.ultimaMensagemRecebida = "Veterinário " + nome + " avisado: atendimento "
                    + atendimento.getCodigo() + " foi cancelado.";
        }
    }
}
