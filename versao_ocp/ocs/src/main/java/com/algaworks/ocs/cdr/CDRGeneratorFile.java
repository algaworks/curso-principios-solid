package com.algaworks.ocs.cdr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CDRGeneratorFile {

	public void gerar(File file, String numero, double tempo, double valorLigacao) {
		try (PrintStream printStream = new PrintStream(new FileOutputStream(file, true))) {
			printStream.println(numero + ":" + tempo + ":" + valorLigacao);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Erro salvando arquivo cdr", e);
		}
	}
	
	public File getFile(String numero, String pastaCdr) {
		return new File(pastaCdr + File.pathSeparator + numero);
	}
	
}
