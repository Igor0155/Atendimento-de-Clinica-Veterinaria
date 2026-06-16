package clinicaveterinaria;

public class Tutor implements ObservadorAtendimento {

    private String nome;
    private String telefone;
    private String ultimaMensagemRecebida;

    public Tutor(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getUltimaMensagemRecebida() { return ultimaMensagemRecebida; }

    @Override
    public void atualizar(Atendimento atendimento, EventoAtendimento evento) {
        if (evento == EventoAtendimento.INICIADO) {
            this.ultimaMensagemRecebida = "Tutor " + nome + " avisado: atendimento do animal "
                    + atendimento.getAnimal().getNome() + " foi iniciado.";
        }
    }
}
