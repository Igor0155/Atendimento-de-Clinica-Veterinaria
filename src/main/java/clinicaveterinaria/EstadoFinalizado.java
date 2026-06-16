package clinicaveterinaria;

public class EstadoFinalizado implements EstadoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento finalizado não pode ser iniciado novamente.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento já está finalizado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento finalizado não pode ser cancelado.");
    }

    @Override
    public String getNome() { return "Finalizado"; }
}
