package com.algaworks.ocs.cdr;

import java.io.File;

public interface CDRGenerator {

	public void gerar(String numero, double tempo, double valorLigacao);

	public File getFile(String numero);

}