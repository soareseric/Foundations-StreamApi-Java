package br.com.ericsoares.stream.api.main;

import java.util.ArrayList;
import java.util.List;

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
		System.out.print("O salario total é R$" + salarioTotal);
	}

}
