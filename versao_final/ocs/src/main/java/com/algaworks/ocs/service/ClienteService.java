package com.algaworks.ocs.service;

import com.algaworks.ocs.api.Autorizavel;
import com.algaworks.ocs.api.Finalizavel;
import com.algaworks.ocs.api.Ligacao;
import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;

public class ClienteService implements Autorizavel, Finalizavel {

	private Clientes clientes;
	private Finalizavel finalizavel;
	
	public ClienteService(Clientes clientes) {
		this.clientes = clientes;
	}

	@Override
	public Ligacao autorizar(String numero) {
		Cliente cliente = clientes.porNumero(numero);
		
		if (cliente.getSaldo() > 0) 
			return criarLigacaoAutorizada(cliente);

		return new Ligacao(false);
	}
	
	@Override
	public void finalizar(Cliente cliente, double tempo) {
		atualizarSaldoCliente(cliente, tempo);
		finalizavel.finalizar(cliente, tempo);
	}

	private void atualizarSaldoCliente(Cliente cliente, double tempo) {
		double saldo = cliente.getSaldo();
		double valorLigacao = cliente.getTarifa().calcularValorLigacao(tempo);
		saldo -= valorLigacao;
		cliente.setSaldo(saldo);
		clientes.guardar(cliente);
	}
	
	private Ligacao criarLigacaoAutorizada(Cliente cliente) {
		double tempoPermitido = (cliente.getSaldo() / cliente.getTarifa().getValor()) * 60;
		return new Ligacao(true, tempoPermitido);
	}

}
