package model;

import java.util.Date;

public class Aula {
    private int id;
    private Date data;
    private int turmaNumero;
    private int professorId;
    
    public Aula() {
    }

    public Aula(int id, Date data, int turmaNumero, int professorId) {
        this.id = id;
        this.data = data;
        this.turmaNumero = turmaNumero;
        this.professorId = professorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getTurmaNumero() {
        return turmaNumero;
    }

    public void setTurmaNumero(int turmaNumero) {
        this.turmaNumero = turmaNumero;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}

