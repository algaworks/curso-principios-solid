package com.algaworks.ocs.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.algaworks.ocs.model.Cliente;

public class OcsApi {

	private static final Logger logger = Logger.getLogger(OcsApi.class);

	private EntityManager manager;
	private String pastaCdr;

	public OcsApi(EntityManager manager, String pastaCdr) {
		this.manager = manager;
		this.pastaCdr = pastaCdr;
	}

	public Ligacao autorizar(String numero) {
		logger.info(String.format("Autorizando ligação para o número: %s", numero));
		
		Cliente cliente = getCliente(numero);
		double saldo = cliente.getSaldo();
		double tarifa = cliente.getTarifa().getValor();
		
		logger.info(String.format("Saldo do número %s é R$%.2f com tarifa de R$%.2f/min", numero, saldo, tarifa));
		
		boolean autorizada;
		double tempoPermitido = -1;
		if (saldo > 0) {
			autorizada = true;
			tempoPermitido = (saldo / tarifa) * 60;
			logger.info(String.format("Tempo permitido, em segundos, para número %s é %.2f", numero, tempoPermitido));
		} else {
			autorizada = false;
			logger.info(String.format("Número %s não tem saldo suficiente para completar ligação.", numero));
		}

		return new Ligacao(autorizada, tempoPermitido);
	}

	public void finalizar(String numero, double tempo) {
		logger.info(String.format("Finalizando ligação para número %s que falou por %.2f segundos", numero, tempo));
		manager.getTransaction().begin();
		Cliente cliente = getCliente(numero);
		double tarifa = cliente.getTarifa().getValor();
		double saldo = cliente.getSaldo();
		double valorLigacao = (tempo / 60) * tarifa;
		saldo -= valorLigacao;
		cliente.setSaldo(saldo);
		manager.getTransaction().commit();
		logger.info(String.format("Chamada finalizada e débito efetuado no valor de R$%.2f para número %s", valorLigacao, numero));
		
		File file = getCDRFile(numero);
		try (PrintStream printStream = new PrintStream(new FileOutputStream(file, true))) {
			printStream.println(numero + ":" + tempo + ":" + valorLigacao);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public File getCDRFile(String numero) {
		return new File(pastaCdr + File.pathSeparator + numero);
	}
	
	private Cliente getCliente(String numero) {
		Cliente cliente = manager.createQuery("from Cliente where numero = :numero", Cliente.class)
				.setParameter("numero", numero)
				.getSingleResult();
		return cliente;
	}

}
