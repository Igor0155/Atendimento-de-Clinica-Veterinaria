package clinicaveterinaria;

public class AdicionalNoturno extends ServicoVeterinarioDecorator {

    public AdicionalNoturno(ServicoVeterinario servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao() + " + adicional noturno (20%)";
    }

    @Override
    public double getValor() {
        // Acrescenta 20% sobre o valor acumulado dos serviços anteriores
        return servico.getValor() * 1.20;
    }
}