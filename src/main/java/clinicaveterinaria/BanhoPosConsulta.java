package clinicaveterinaria;

public class BanhoPosConsulta extends ServicoVeterinarioDecorator {

    public BanhoPosConsulta(ServicoVeterinario servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao() + " + banho pós-consulta";
    }

    @Override
    public double getValor() {
        return servico.getValor() + 80.00;
    }
}
