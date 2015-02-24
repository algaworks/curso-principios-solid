package com.algaworks.ocs.repository;

import javax.persistence.EntityManager;

import com.algaworks.ocs.model.Cliente;

public class Clientes {
	
	private EntityManager manager;
	
	public Cliente porNumero(String numero) {
		Cliente cliente = manager.createQuery("from Cliente where numero = :numero", Cliente.class)
				.setParameter("numero", numero)
				.getSingleResult();
		return cliente;
	}
	
	public void guardar(Cliente cliente) {
		manager.getTransaction().begin();
		manager.merge(cliente);
		manager.getTransaction().commit();
	}
	
}
