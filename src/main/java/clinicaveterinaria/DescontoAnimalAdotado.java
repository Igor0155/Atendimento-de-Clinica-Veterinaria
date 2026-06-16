package clinicaveterinaria;

public class DescontoAnimalAdotado extends ServicoVeterinarioDecorator {

    public DescontoAnimalAdotado(ServicoVeterinario servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao() + " + desconto animal adotado";
    }

    @Override
    public double getValor() {
        return servico.getValor() * 0.90;
    }
}
