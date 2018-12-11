package br.com.ericsoares.stream.api.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import br.com.ericsoares.stream.api.impl.Mensageiro;
import br.com.ericsoares.stream.api.models.Empregado;

public class Main {

	public static void main(String[] args) {

		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(new Empregado(1, "José", 2000, "RH"));
		empregados.add(new Empregado(2, "Fulano", 3000, "Contabeis"));
		empregados.add(new Empregado(3, "Tadeu", 6000, "Analista"));

		// SEM UTILIZAR STREAM API
//		for(Empregado emp : empregados) {
//			System.out.println(emp.getNome());
//		}

		// UTILIZANDO STREAM API
		empregados.stream().forEach(emp -> {
			System.out.println(emp.getNome());
		});

		// SEM UTILIZAR STREAM API
//		double salarioTotal = 0;
//		for(Empregado emp : empregados) {
//			salarioTotal = salarioTotal + emp.getSalario();
//		}

		// UTILIZANDO STREAM API
		double salarioTotal = empregados.stream().mapToDouble(emp -> emp.getSalario()).sum();
		System.out.println("O salario total é R$" + salarioTotal);

		// UTILIZANDO EXPRESSAO LAMBDA ATRAVES DE UMA INTERFACE FUNCIONAL
		Mensageiro mensageiro = (mensagem) -> System.out.println("Mensagem da expressao lambda: " + mensagem);
		mensageiro.enviarMensagem("Hello Lambda");
		
		System.out.println("******************** INTERFACES FUNCIONAIS LAMBDA *********************");
		System.out.println();
		
		// Consumer
		// ENTRA UM PARAMETRO E NAO RETORNA NADA!
		System.out.println("Execução do Consumer: ");
		Consumer<Empregado> consumer = (emp) -> System.out.println("O nome do empregado é " + emp.getNome());
		consumer.accept(new Empregado(1, "Eric", 30000, "Analista de sistemas"));
		System.out.println();
		
		// Function
		// 
		// RESTRIÇÕES: SOMENTE É COMPATIVEL COM REFERENCE TYPES!
		System.out.println("Execução da Function: ");
		Function<Empregado, Double> function = (emp) -> emp.getSalario() * 10;
		double novoSalario = function.apply(new Empregado(1, "Eric", 40000, "Analista de sistemas"));
		System.out.printf("O novo salario do empregado é R$ %.2f", novoSalario);
		System.out.println();
		System.out.println();
		
		// BinaryOperator
		//
		System.out.println("Execução do BinaryOperator: ");
		BinaryOperator<Empregado> binaryOperator = (emp1, emp2) -> new Empregado(2, emp1.getNome() + emp2.getNome(), emp1.getSalario() + emp2.getSalario(), emp1.getDepartamento() + emp2.getDepartamento());
		Empregado novoEmpregado = binaryOperator.apply(new Empregado(10, "Eric", 10000, "Analista de "), new Empregado(11, " Soares", 20000, "Sistemas"));
		System.out.printf("O novo empregadado se chama %s e seu salario é R$ %.2f e ele é %s", novoEmpregado.getNome(), novoEmpregado.getSalario(), novoEmpregado.getDepartamento());
		System.out.println();
		System.out.println();
		
		
		// Predicate
		// Apenas retorna um Boolean
		System.out.println("Execução do Predicate: ");
		Predicate<Empregado> predicate = (emp) -> emp.getNome().endsWith("Soares");
		Boolean terminaComWeb = predicate.test(new Empregado(1, "Eric Soares", 30000, ""));
		System.out.println("O nome do empregado termina com Soares? " + terminaComWeb);
		System.out.println();
		
		// Supplier
		// 
		System.out.println("Execução do Supplier: ");
		Supplier<Empregado> supplier = () -> new Empregado(new Random().nextInt(), "", 0, "");
		Empregado emp1 = supplier.get();
		System.out.println(emp1.getId());
		Empregado emp2 = supplier.get();
		System.out.println(emp2.getId());
	}
}
