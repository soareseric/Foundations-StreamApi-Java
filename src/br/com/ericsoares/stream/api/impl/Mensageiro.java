package br.com.ericsoares.stream.api.impl;

@FunctionalInterface
public interface Mensageiro {

	void enviarMensagem(String mensagem);
}
