package model;

public class Turma {
    private int numero;
    private Disciplina disciplina;

    public Turma(int numero, Disciplina disciplina) {
        this.numero = numero;
        this.disciplina = disciplina;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Disciplina getDisciplinaOfertada() {
        return disciplina;
    }

    public void setDisciplinaOfertada(Disciplina disciplinaOfertada) {
        this.disciplina = disciplinaOfertada;
    }
}
