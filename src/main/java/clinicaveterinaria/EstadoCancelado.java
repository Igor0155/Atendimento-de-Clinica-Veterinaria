package clinicaveterinaria;

public class EstadoCancelado implements EstadoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento cancelado não pode ser iniciado.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento cancelado não pode ser finalizado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new IllegalStateException("Atendimento já está cancelado.");
    }

    @Override
    public String getNome() { return "Cancelado"; }
}
