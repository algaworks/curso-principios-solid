package com.algaworks.ocs.cdr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CDRGeneratorFile implements CDRGenerator, CDRFileLocator {

	private String pastaCdr;
	
	public CDRGeneratorFile(String pastaCdr) {
		this.pastaCdr = pastaCdr;
	}

	@Override
	public void gerar(String numero, double tempo, double valorLigacao) {
		File file = getFile(numero);
		try (PrintStream printStream = new PrintStream(new FileOutputStream(file, true))) {
			printStream.println(numero + ":" + tempo + ":" + valorLigacao);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Erro salvando arquivo cdr", e);
		}
	}
	
	@Override
	public File getFile(String numero) {
		return new File(pastaCdr + File.pathSeparator + numero);
	}
	
}
