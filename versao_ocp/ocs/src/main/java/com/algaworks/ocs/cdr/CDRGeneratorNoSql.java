package com.algaworks.ocs.cdr;

import java.io.File;

public class CDRGeneratorNoSql extends CDRGeneratorFile {

	@Override
	public void gerar(File file, String numero, double tempo, double valorLigacao) {
		// Salvar no NoSQL
	}

	@Override
	public File getFile(String numero, String pastaCdr) {
		return super.getFile(numero, pastaCdr);
	}

}
