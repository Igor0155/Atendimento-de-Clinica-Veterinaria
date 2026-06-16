package clinicaveterinaria;

public class TaxaAtendimentoDomiciliar extends ServicoVeterinarioDecorator {

    public TaxaAtendimentoDomiciliar(ServicoVeterinario servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao() + " + atendimento domiciliar";
    }

    @Override
    public double getValor() {
        return servico.getValor() + 50.00;
    }
}
