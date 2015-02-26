package com.algaworks.ocs.api;

import com.algaworks.ocs.model.Cliente;

public interface Finalizavel {

	public void finalizar(Cliente cliente, double tempo);
	
}
