package com.algaworks.ocs.cdr;

import com.algaworks.ocs.api.Finalizavel;
import com.algaworks.ocs.model.Cliente;

public class CDRGeneratorNoSql implements Finalizavel {

	@Override
	public void finalizar(Cliente cliente, double tempo) {
		gerar(cliente.getNumero(), tempo, cliente.getTarifa().calcularValorLigacao(tempo));
	}
	
	private void gerar(String numero, double tempo, double valorLigacao) {
		// Salvar no NoSQL
	}

}
