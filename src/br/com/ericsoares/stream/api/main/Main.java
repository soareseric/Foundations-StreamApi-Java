package br.com.ericsoares.stream.api.main;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.ericsoares.stream.api.models.Empregado;

public class Main {

	public static void main(String[] args) {

		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(new Empregado(1, "José", 2000, "RH"));
		empregados.add(new Empregado(2, "Julano", 3000, "Contabeis"));
		empregados.add(new Empregado(3, "Tadeu", 6000, "Analista"));

		System.out.println("** FUNCIONÁROS QUE COMEÇAM COM J **");
		//STREAM NÃO PODEM SER REAPROVEITADOS
		//STREAM PODEM SOFRER AÇÕES DE TERMINAÇÃO
		
		Stream<Empregado> stream = empregados.stream().filter(emp -> {
			System.out.println("invocando o método filter");
			return emp.getNome().startsWith("J");
		});
		System.out.println("A Stream API é lazy loading! Comprovado com esse sysout. Que foi executado primeiro que o sysout do filter");
		List<Empregado> empregadosComJ = stream.collect(Collectors.toList());
		
		empregadosComJ.stream().forEach((emp) -> System.out.println(emp.getNome()));
		OptionalDouble menorSalario = empregadosComJ.stream().mapToDouble((emp) -> emp.getSalario()).min();
		if (menorSalario.isPresent()) {
			System.out.println("O menor salario é R$ " + menorSalario.getAsDouble());
		}
		
		
		
		
		
	}
}
