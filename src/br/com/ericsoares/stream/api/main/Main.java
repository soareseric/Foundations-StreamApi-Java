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

		// INSTANCIANDO UMA LISTA DE EMPREGADOS. E ADICIONANDO VALORES A ELA.
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
		

		// STREAM API : MÉTODO FILTER
		// RESTRIÇÕES : É NECESSARIO SEMPRE RETORNAR UM BOOLEAN !
		Stream<Empregado> stream = empregados.stream().filter(emp -> {
			System.out.println("invocando o método filter");
			return emp.getNome().startsWith("J");
		});
		
		
		// DEMOSTRAÇÃO DO LAZY LOADING. POIS, A STREAM API É LAZY LOADING !
		System.out.println(
				"A Stream API é lazy loading! Comprovado com esse sysout. Que foi executado primeiro que o sysout do filter");
		
		// STREAM API : MÉTODO COLLECT
		// CONVERTENDO UMA STREAM, PARA UMA LIST
		List<Empregado> empregadosComJ = stream.collect(Collectors.toList());
		empregadosComJ.stream().forEach((emp) -> System.out.println(emp.getNome()));
		
		// STREAM API : MÉTODO MAPTODOUBLE
		// MAPEIA O OBJETO A SER ATRIBUIDO, BUSCANDO ESPECIFICAMENTE TODOS OS VALORES DO ATRIBUTO DOUBLE
		OptionalDouble menorSalario = empregadosComJ.stream().mapToDouble((emp) -> emp.getSalario()).min();
		// COMO A EXISTENCIA DE UM ATRIBUTO DOUBLE PODE SER OPCIONAL, SE ARMAZENA A VARIAVEL EM UM OPTIONAL DOUBLE
		// LOGO EM SEGUIDA, FAZ-SE UMA VERIFICAÇÃO SE REALMENTE EXISTE VALORES ATRIBUIDOS A ESSE ATRIBUTO
		if (menorSalario.isPresent()) {
			System.out.println("O menor salario é R$ " + menorSalario.getAsDouble());
		}
		
		
		// STREAM API : COLLECT - COLLECTORS - SUMMARIZINGDOUBLE
		// É POSSIVEL ATRAVÊS DELE, CONSEGUIR REALIZAR ESTATISTICAS
		DoubleSummaryStatistics sumario = empregados.stream()
				.collect(Collectors.summarizingDouble(Empregado::getSalario)); // Reference method, pode ser usado no
																				// lugar de expressoes lambdas
		System.out.println("Estatisticas do salário: ");
		System.out.println("Maior salário: R$ " + sumario.getMax());
		System.out.println("Menor salário: R$ " + sumario.getMin());
		System.out.printf("Média salarial: R$ %.1f ", sumario.getAverage());
		System.out.println();
		System.out.println("Folha salarial: R$ " + sumario.getSum());

		// STREAM API : MÉTODO MAP
		// COMO FALADO BREVIAMENTE, ELE MAPEIA O QUE É PASSADO PARA ELE. NO CASO OS NOMES DOS EMPREGADOS, APOS
		// MAPEAR, TRANSFORMAMOS O RESULTADO EM UMA LISTA
		List<String> nomesEmpregados = empregados.stream().map(emp -> emp.getNome()).collect(Collectors.toList());
		nomesEmpregados.forEach(System.out::println);

		// STREAM API : MÉTODO REDUCE
		// COMO O NOME PROPRIAMENTE DIZ, ELE REDUZ ALGO. NO CASO, IREMOS AO INVES DE TRANSFORMAR EM UMA LISTA
		// O NOME DOS USUARIOS QUE MAPEAMOS ATRAVES DO MÉTODO MAP, ARMAZENAREMOS TUDO EM UMA UNICA STRING
		String nomesSeparadosPorVirgula = empregados.stream().map(emp -> emp.getNome()).reduce("Nomes dos empregados: ",
				(n1, n2) -> n1 + ", " + n2);
		System.out.println(nomesSeparadosPorVirgula);
		System.out.println();
		
		// STREAM API : MÉTODO GROUPINGBY
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
