package clinicaveterinaria;

import java.util.ArrayList;
import java.util.List;

public class AtendimentoBuilder {

    private String codigo;
    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servico;
    // Criamos uma lista temporária para guardar os observadores durante a
    // construção
    private List<ObservadorAtendimento> observadores = new ArrayList<>();

    public AtendimentoBuilder(String codigo) {
        this.codigo = codigo;
    }

    public AtendimentoBuilder comTutor(Tutor tutor) {
        this.tutor = tutor;
        return this;
    }

    public AtendimentoBuilder comAnimal(Animal animal) {
        this.animal = animal;
        return this;
    }

    public AtendimentoBuilder comServicoBase(ServicoVeterinario servicoBase) {
        this.servico = servicoBase;
        return this;
    }

    public AtendimentoBuilder adicionarRegraDePreco(
            java.util.function.Function<ServicoVeterinario, ServicoVeterinario> decorator) {
        this.servico = decorator.apply(this.servico);
        return this;
    }

    // Agora o observador é guardado no próprio Builder, e retorna o Builder para
    // permitir o encadeamento
    public AtendimentoBuilder adicionarObservador(ObservadorAtendimento observador) {
        this.observadores.add(observador);
        return this;
    }

    // O build() é a última etapa. Ele cria o objeto e despeja os observadores nele.
    public Atendimento build() {
        if (codigo == null || tutor == null || animal == null || servico == null) {
            throw new IllegalStateException("Atendimento não pode ser criado sem os dados obrigatórios.");
        }

        Atendimento atendimento = new Atendimento(codigo, tutor, animal, servico);

        // Adiciona todos os observadores que foram acumulados no builder
        for (ObservadorAtendimento obs : observadores) {
            atendimento.adicionarObservador(obs);
        }

        return atendimento;
    }
}