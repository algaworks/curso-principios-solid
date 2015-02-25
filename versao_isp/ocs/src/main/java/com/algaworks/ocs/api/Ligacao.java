package com.algaworks.ocs.api;

public class Ligacao {

	private boolean autorizada;
	private double tempo;

	public Ligacao(boolean autorizada, double tempo) {
		this.autorizada = autorizada;
		this.tempo = tempo;
	}

	public boolean isAutorizada() {
		return autorizada;
	}

	public void setAutorizada(boolean autorizada) {
		this.autorizada = autorizada;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

}
