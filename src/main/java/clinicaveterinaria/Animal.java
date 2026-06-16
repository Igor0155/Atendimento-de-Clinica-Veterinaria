package clinicaveterinaria;

public class Animal {

    private String nome;
    private String especie;
    private int idade;
    private boolean adotado;

    public Animal(String nome, String especie, int idade, boolean adotado) {
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.adotado = adotado;
    }

    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public int getIdade() { return idade; }
    public boolean isAdotado() { return adotado; }
}
