package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaEntityManager {
	private static EntityManagerFactory emf;
	
	static { //Sempre vai fazer isso quando for iniciada
		emf = Persistence.createEntityManagerFactory("Vendas");
	}

	private FabricaEntityManager() {
	
	}
	
	public static EntityManager getEntityManager() {
		if(emf==null) {
			return null;
		}else {
			return emf.createEntityManager();
		}
	}
}
