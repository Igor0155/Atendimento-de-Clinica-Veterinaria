package clinicaveterinaria;

public class ServicoBase implements ServicoVeterinario {

    private String descricao;
    private double valorBase;

    public ServicoBase(String descricao, double valorBase) {
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    @Override
    public String getDescricao() { return descricao; }

    @Override
    public double getValor() { return valorBase; }
}
