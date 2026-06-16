package clinicaveterinaria;

public class DescontoConvenioONG extends ServicoVeterinarioDecorator {

    public DescontoConvenioONG(ServicoVeterinario servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao() + " + convênio ONG parceira";
    }

    @Override
    public double getValor() {
        // Desconto de 30% para ONGs parceiras
        return servico.getValor() * 0.70;
    }
}