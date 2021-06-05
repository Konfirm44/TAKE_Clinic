package pl.take.clinic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class Test {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinic");
		EntityManager em = factory.createEntityManager();
		
		
		
		em.close();
	}
}
