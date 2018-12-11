package br.com.ericsoares.stream.api.main;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.ericsoares.stream.api.models.Empregado;

public class Main {

	public static void main(String[] args) {

		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(new Empregado(1, "José", 2000, "RH"));
		empregados.add(new Empregado(2, "Julano", 3000, "RH"));
		empregados.add(new Empregado(3, "Tadeu", 6000, "Analista"));
		empregados.add(new Empregado(4, "Jubileu", 4000, "Analista"));
		empregados.add(new Empregado(5, "Eoclides", 2000, "Programador"));
		empregados.add(new Empregado(6, "Pereira", 11000, "Gerente"));

		System.out.println("** FUNCIONÁROS QUE COMEÇAM COM J **");
		// STREAM NÃO PODEM SER REAPROVEITADOS
		// STREAM PODEM SOFRER AÇÕES DE TERMINAÇÃO

		Stream<Empregado> stream = empregados.stream().filter(emp -> {
			System.out.println("invocando o método filter");
			return emp.getNome().startsWith("J");
		});
		System.out.println(
				"A Stream API é lazy loading! Comprovado com esse sysout. Que foi executado primeiro que o sysout do filter");
		List<Empregado> empregadosComJ = stream.collect(Collectors.toList());

		empregadosComJ.stream().forEach((emp) -> System.out.println(emp.getNome()));
		OptionalDouble menorSalario = empregadosComJ.stream().mapToDouble((emp) -> emp.getSalario()).min();
		if (menorSalario.isPresent()) {
			System.out.println("O menor salario é R$ " + menorSalario.getAsDouble());
		}

		DoubleSummaryStatistics sumario = empregados.stream()
				.collect(Collectors.summarizingDouble(Empregado::getSalario)); // Reference method, pode ser usado no
																				// lugar de expressoes lambdas
		System.out.println("Estatisticas do salário: ");
		System.out.println("Maior salário: R$ " + sumario.getMax());
		System.out.println("Menor salário: R$ " + sumario.getMin());
		System.out.printf("Média salarial: R$ %.1f ", sumario.getAverage());
		System.out.println();
		System.out.println("Folha salarial: R$ " + sumario.getSum());

		List<String> nomesEmpregados = empregados.stream().map(emp -> emp.getNome()).collect(Collectors.toList());
		nomesEmpregados.forEach(System.out::println);

		String nomesSeparadosPorVirgula = empregados.stream().map(emp -> emp.getNome()).reduce("Nomes dos empregados: ",
				(n1, n2) -> n1 + ", " + n2);
		System.out.println(nomesSeparadosPorVirgula);
		System.out.println();
		
		// MÉTODO GROUPINGBY
		Map<String, List<Empregado>> dadosDepartamento = empregados.stream()
				.collect(Collectors.groupingBy(Empregado::getDepartamento));

		System.out.println("** Empregados por departamento: **");
		dadosDepartamento.forEach((dep, emps) -> {
			System.out.println(" - " + dep + ", " + emps.size() + " funcionários");
			emps.forEach(emp -> {
				System.out.println("	  *" + emp.getNome());
			});
		});

	}
}
