package br.com.ericsoares.stream.api.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import br.com.ericsoares.stream.api.models.Empregado;

public class Main {

	public static void main(String[] args) {

		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(new Empregado(1, "José", 2000, "RH"));
		empregados.add(new Empregado(2, "Julano", 3000, "Contabeis"));
		empregados.add(new Empregado(3, "Tadeu", 6000, "Analista"));

		System.out.println("** FUNCIONÁROS QUE COMEÇAM COM J **");
		Stream<Empregado> streamEmpregados = empregados.stream();
		Stream<Empregado> empregadosComcecamComJ = streamEmpregados.filter(emp -> emp.getNome().startsWith("J"));
		empregadosComcecamComJ.forEach((emp) -> System.out.println(emp.getNome()));
		
	}
}
