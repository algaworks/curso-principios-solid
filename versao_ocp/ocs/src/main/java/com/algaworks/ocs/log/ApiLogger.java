package com.algaworks.ocs.log;

import org.apache.log4j.Logger;

public class ApiLogger {

	private static final Logger logger = Logger.getLogger(ApiLogger.class);
	
	public void autorizandoLigacao(String numero) {
		logger.info(String.format("Autorizando ligação para o número: %s", numero));
	}
	
	public void ligacaoAutorizada(String numero, double tempoPermitido) {
		logger.info(String.format("Tempo permitido, em segundos, para número %s é %.2f", numero, tempoPermitido));
	}
	
	public void ligacaoNegada(String numero) {
		logger.info(String.format("Número %s não tem saldo suficiente para completar ligação.", numero));
	}
	
	public void finalizandoLigacao(String numero, double tempo) {
		logger.info(String.format("Finalizando ligação para número %s que falou por %.2f segundos", numero, tempo));
	}
	
	public void chamadaFinalizada(String numero, double valorLigacao) {
		logger.info(String.format("Chamada finalizada e débito efetuado no valor de R$%.2f para número %s", valorLigacao, numero));
	}
}
