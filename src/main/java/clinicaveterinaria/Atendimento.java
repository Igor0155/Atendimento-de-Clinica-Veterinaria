package clinicaveterinaria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Atendimento {

    private String codigo;
    private Tutor tutor;
    private Animal animal;
    private ServicoVeterinario servico;
    private EstadoAtendimento estado;
    private List<ObservadorAtendimento> observadores;

    public Atendimento(String codigo, Tutor tutor, Animal animal, ServicoVeterinario servico) {
        this.codigo = codigo;
        this.tutor = tutor;
        this.animal = animal;
        this.servico = servico;
        this.estado = new EstadoAgendado();
        this.observadores = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public ServicoVeterinario getServico() {
        return servico;
    }

    public EstadoAtendimento getEstado() {
        return estado;
    }

    public String getSituacaoAtual() {
        return estado.getNome();
    }

    public void setServico(ServicoVeterinario servico) {
        this.servico = servico;
    }

    public void setEstado(EstadoAtendimento estado) {
        this.estado = estado;
    }

    public double calcularValorFinal() {
        return servico.getValor();
    }

    public String getDescricaoServico() {
        return servico.getDescricao();
    }

    public void adicionarObservador(ObservadorAtendimento observador) {
        this.observadores.add(observador);
    }

    public void removerObservador(ObservadorAtendimento observador) {
        this.observadores.remove(observador);
    }

    public List<ObservadorAtendimento> getObservadores() {
        return Collections.unmodifiableList(observadores);
    }

    public void notificarObservadores(EventoAtendimento evento) {
        for (ObservadorAtendimento observador : observadores) {
            observador.atualizar(this, evento);
        }
    }

    public void iniciar() {
        estado.iniciar(this);
    }

    public void finalizar() {
        estado.finalizar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }
}
