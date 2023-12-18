package model;

public class Aluno {
	 private String nome;
	 private int matricula;
	 private int faltas;
	 
	public Aluno(int matricula, String nome, int faltas) {
	    this.matricula = matricula;
	    this.nome = nome;
	    this.faltas = faltas;
	 }
	 
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public int getFaltas() {
		return faltas;
	}
	
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
}
