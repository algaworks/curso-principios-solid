package com.algaworks.ocs.cdr;

import java.io.File;

public class CDRGeneratorNoSql implements CDRGenerator {

	@Override
	public void gerar(String numero, double tempo, double valorLigacao) {
		// Salvar no NoSQL
	}

	@Override
	public File getFile(String numero) {
		throw new UnsupportedOperationException();
	}

}
