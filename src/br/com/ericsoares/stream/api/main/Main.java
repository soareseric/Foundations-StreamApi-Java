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
		
		for(Empregado emp : empregados) {
			System.out.println(emp.getNome());
		}
		
		double salarioTotal = 0;
		for(Empregado emp : empregados) {
			salarioTotal = salarioTotal + emp.getSalario();
		}
		System.out.println("O salario total é R$ " + salarioTotal);
	}

}
