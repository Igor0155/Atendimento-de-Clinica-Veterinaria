package clinicaveterinaria;

public class ControleMedicacao implements ObservadorAtendimento {

    private String statusFarmacia;

    public String getStatusFarmacia() {
        return statusFarmacia;
    }

    @Override
    public void atualizar(Atendimento atendimento, EventoAtendimento evento) {
        if (evento == EventoAtendimento.INICIADO) {
            this.statusFarmacia = "Farmácia notificada: Preparar kit de triagem e controle de medicação para o animal "
                    + atendimento.getAnimal().getNome();
        }
    }
}