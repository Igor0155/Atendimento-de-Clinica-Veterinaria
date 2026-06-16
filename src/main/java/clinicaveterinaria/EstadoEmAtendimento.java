package clinicaveterinaria;

public class EstadoEmAtendimento implements EstadoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento já está em andamento.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        atendimento.setEstado(new EstadoFinalizado());
        atendimento.notificarObservadores(EventoAtendimento.FINALIZADO);
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento em andamento não pode ser cancelado diretamente.");
    }

    @Override
    public String getNome() { return "EmAtendimento"; }
}
