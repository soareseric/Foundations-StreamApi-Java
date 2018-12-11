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
		empregados.add(new Empregado(1, "Jos�", 2000, "RH"));
		empregados.add(new Empregado(2, "Julano", 3000, "RH"));
		empregados.add(new Empregado(3, "Tadeu", 6000, "Analista"));
		empregados.add(new Empregado(4, "Jubileu", 4000, "Analista"));
		empregados.add(new Empregado(5, "Eoclides", 2000, "Programador"));
		empregados.add(new Empregado(6, "Pereira", 11000, "Gerente"));

		System.out.println("** FUNCION�ROS QUE COME�AM COM J **");
		// STREAM N�O PODEM SER REAPROVEITADOS
		// STREAM PODEM SOFRER A��ES DE TERMINA��O
		

		// STREAM API : M�TODO FILTER
		// RESTRI��ES : � NECESSARIO SEMPRE RETORNAR UM BOOLEAN !
		Stream<Empregado> stream = empregados.stream().filter(emp -> {
			System.out.println("invocando o m�todo filter");
			return emp.getNome().startsWith("J");
		});
		
		
		// DEMOSTRA��O DO LAZY LOADING. POIS, A STREAM API � LAZY LOADING !
		System.out.println(
				"A Stream API � lazy loading! Comprovado com esse sysout. Que foi executado primeiro que o sysout do filter");
		
		// STREAM API : M�TODO COLLECT
		// CONVERTENDO UMA STREAM, PARA UMA LIST
		List<Empregado> empregadosComJ = stream.collect(Collectors.toList());
		empregadosComJ.stream().forEach((emp) -> System.out.println(emp.getNome()));
		
		// STREAM API : M�TODO MAPTODOUBLE
		// MAPEIA O OBJETO A SER ATRIBUIDO, BUSCANDO ESPECIFICAMENTE TODOS OS VALORES DO ATRIBUTO DOUBLE
		OptionalDouble menorSalario = empregadosComJ.stream().mapToDouble((emp) -> emp.getSalario()).min();
		// COMO A EXISTENCIA DE UM ATRIBUTO DOUBLE PODE SER OPCIONAL, SE ARMAZENA A VARIAVEL EM UM OPTIONAL DOUBLE
		// LOGO EM SEGUIDA, FAZ-SE UMA VERIFICA��O SE REALMENTE EXISTE VALORES ATRIBUIDOS A ESSE ATRIBUTO
		if (menorSalario.isPresent()) {
			System.out.println("O menor salario � R$ " + menorSalario.getAsDouble());
		}
		
		
		// STREAM API : COLLECT - COLLECTORS - SUMMARIZINGDOUBLE
		// � POSSIVEL ATRAV�S DELE, CONSEGUIR REALIZAR ESTATISTICAS
		DoubleSummaryStatistics sumario = empregados.stream()
				.collect(Collectors.summarizingDouble(Empregado::getSalario)); // Reference method, pode ser usado no
																				// lugar de expressoes lambdas
		System.out.println("Estatisticas do sal�rio: ");
		System.out.println("Maior sal�rio: R$ " + sumario.getMax());
		System.out.println("Menor sal�rio: R$ " + sumario.getMin());
		System.out.printf("M�dia salarial: R$ %.1f ", sumario.getAverage());
		System.out.println();
		System.out.println("Folha salarial: R$ " + sumario.getSum());

		// STREAM API : M�TODO MAP
		// COMO FALADO BREVIAMENTE, ELE MAPEIA O QUE � PASSADO PARA ELE. NO CASO OS NOMES DOS EMPREGADOS, APOS
		// MAPEAR, TRANSFORMAMOS O RESULTADO EM UMA LISTA
		List<String> nomesEmpregados = empregados.stream().map(emp -> emp.getNome()).collect(Collectors.toList());
		nomesEmpregados.forEach(System.out::println);

		// STREAM API : M�TODO REDUCE
		// COMO O NOME PROPRIAMENTE DIZ, ELE REDUZ ALGO. NO CASO, IREMOS AO INVES DE TRANSFORMAR EM UMA LISTA
		// O NOME DOS USUARIOS QUE MAPEAMOS ATRAVES DO M�TODO MAP, ARMAZENAREMOS TUDO EM UMA UNICA STRING
		String nomesSeparadosPorVirgula = empregados.stream().map(emp -> emp.getNome()).reduce("Nomes dos empregados: ",
				(n1, n2) -> n1 + ", " + n2);
		System.out.println(nomesSeparadosPorVirgula);
		System.out.println();
		
		// STREAM API : M�TODO GROUPINGBY
		Map<String, List<Empregado>> dadosDepartamento = empregados.stream()
				.collect(Collectors.groupingBy(Empregado::getDepartamento));

		System.out.println("** Empregados por departamento: **");
		dadosDepartamento.forEach((dep, emps) -> {
			System.out.println(" - " + dep + ", " + emps.size() + " funcion�rios");
			emps.forEach(emp -> {
				System.out.println("	  *" + emp.getNome());
			});
		});

	}
}
