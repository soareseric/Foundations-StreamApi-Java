package br.com.ericsoares.stream.api.models;

public class Empregado {

	private long id;
	private String nome;
	private double salario;
	private String departamento;

	
	public Empregado(long id, String nome, double salario, String departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.salario = salario;
		this.departamento = departamento;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNoame(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
