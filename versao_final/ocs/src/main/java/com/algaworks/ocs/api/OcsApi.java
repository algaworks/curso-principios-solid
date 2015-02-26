package com.algaworks.ocs.api;

import java.io.File;

import com.algaworks.ocs.cdr.CDRFileLocator;
import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;

public class OcsApi {

	private Clientes clientes;
	
	private CDRFileLocator cdrFileLocator;
	
	private Autorizavel autorizavel;
	private Finalizavel finalizavel;

	public OcsApi(CDRFileLocator cdrFileLocator, Clientes clientes
			, Autorizavel autorizavel, Finalizavel finalizavel) {
		this.cdrFileLocator = cdrFileLocator;
		this.clientes = clientes;;
	}

	public Ligacao autorizar(String numero) {
		return autorizavel.autorizar(numero);
	}

	public void finalizar(String numero, double tempo) {
		Cliente cliente = getCliente(numero);
		finalizavel.finalizar(cliente, tempo);
	}
	
	public File getCDRFile(String numero) {
		return cdrFileLocator.getFile(numero);
	}
	
	private Cliente getCliente(String numero) {
		return clientes.porNumero(numero);
	}

}
