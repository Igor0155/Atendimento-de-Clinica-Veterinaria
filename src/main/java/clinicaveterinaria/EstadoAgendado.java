package clinicaveterinaria;

public class EstadoAgendado implements EstadoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        atendimento.setEstado(new EstadoEmAtendimento());
        atendimento.notificarObservadores(EventoAtendimento.INICIADO);
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento agendado não pode ser finalizado antes de ser iniciado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        atendimento.setEstado(new EstadoCancelado());
        atendimento.notificarObservadores(EventoAtendimento.CANCELADO);
    }

    @Override
    public String getNome() { return "Agendado"; }
}
