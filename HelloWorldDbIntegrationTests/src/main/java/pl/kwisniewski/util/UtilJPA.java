package pl.kwisniewski.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UtilJPA {
	
	private static EntityManager em;
	private static EntityTransaction transaction;
	
	public static EntityManager createEntityManager(){
		
		em = Persistence.createEntityManagerFactory("pu").createEntityManager();
		transaction = em.getTransaction();
		transaction.begin();
		return em;
		
	}
	
	public static void closeEntityManager(){
		
		transaction.commit();		
		em.close();
		
	}

}
