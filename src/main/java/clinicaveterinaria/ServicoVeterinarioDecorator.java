package clinicaveterinaria;

public abstract class ServicoVeterinarioDecorator implements ServicoVeterinario {

    protected ServicoVeterinario servico;

    public ServicoVeterinarioDecorator(ServicoVeterinario servico) {
        this.servico = servico;
    }
}
