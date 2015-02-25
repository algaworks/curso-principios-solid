package com.algaworks.ocs.api;

import java.io.File;

import com.algaworks.ocs.cdr.CDRGeneratorFile;
import com.algaworks.ocs.log.ApiLogger;
import com.algaworks.ocs.model.Cliente;
import com.algaworks.ocs.repository.Clientes;

public abstract class OcsApi {

	private String pastaCdr;
	
	private ApiLogger logger;
	private Clientes clientes;
	//private CDRGenerator cdrGenerator;

	public OcsApi(String pastaCdr) {
		this.pastaCdr = pastaCdr;
		
		this.logger = new ApiLogger();
		this.clientes = new Clientes();
		//this.cdrGenerator = new CDRGenerator();
	}

	public Ligacao autorizar(String numero) {
		logger.autorizandoLigacao(numero);
		
		Cliente cliente = getCliente(numero);
		double saldo = cliente.getSaldo();
		double tarifa = cliente.getTarifa().getValor();
		
		boolean autorizada;
		double tempoPermitido = -1;
		if (saldo > 0) {
			autorizada = true;
			tempoPermitido = (saldo / tarifa) * 60;
			logger.ligacaoAutorizada(numero, tempoPermitido);
		} else {
			autorizada = false;
			logger.ligacaoNegada(numero);
		}

		return new Ligacao(autorizada, tempoPermitido);
	}

	public void finalizar(String numero, double tempo) {
		logger.finalizandoLigacao(numero, tempo);
		Cliente cliente = getCliente(numero);
		double tarifa = cliente.getTarifa().getValor();
		double saldo = cliente.getSaldo();
		double valorLigacao = (tempo / 60) * tarifa;
		saldo -= valorLigacao;
		cliente.setSaldo(saldo);
		clientes.guardar(cliente);
		logger.chamadaFinalizada(numero, valorLigacao);
		
		File file = getCDRFile(numero);
		getCdrGenerator().gerar(file, numero, tempo, valorLigacao);
	}
	
	public File getCDRFile(String numero) {
		return getCdrGenerator().getFile(numero, pastaCdr);
	}
	
	private Cliente getCliente(String numero) {
		return clientes.porNumero(numero);
	}

	public abstract CDRGeneratorFile getCdrGenerator();
	
}
