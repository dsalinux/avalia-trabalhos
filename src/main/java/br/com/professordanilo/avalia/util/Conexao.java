package br.com.professordanilo.avalia.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	private EntityManager em = null;
	
	 public EntityManager getConexao() {
	        if (em == null) {
	            EntityManagerFactory factory = Persistence.createEntityManagerFactory("avaliaPU");
	            em = factory.createEntityManager();
	        }
	        return em;
	    }

	    public void fecharConexao() {
	        if (em != null) {
	            em.clear();
//	            em = null;
	        }
	    }
}
