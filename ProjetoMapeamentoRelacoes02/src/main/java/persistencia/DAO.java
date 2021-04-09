package persistencia;

import javax.persistence.EntityManager;

/* Classe responsavel por inserir os dados no banco de dados.
 * É generico para manejar qualquer objeto.
 */

public class DAO<VO> {
	protected EntityManager entityManager;
	
	//Insere a "conexao" no atributo
	public DAO(EntityManager entityManager)throws PersistenciaException {
		this.entityManager = entityManager;
	}
	
	//inclui no banco de dados
	public void incluir(VO vo)throws PersistenciaException{
		try{
			this.entityManager.persist(vo); //inclui no banco
		}catch(Exception e){
			throw new PersistenciaException("Erro ao incluir " + vo.getClass()+" − " + e.getMessage());
		}
	}
	
	//obter o Entity Manager	
	public EntityManager getEntityManager(){
		return entityManager ;
	}
		
	//altera o Entity Manager		
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
			
	//inicialização da transação
	public void beginTransaction(){
		this.entityManager.getTransaction().begin();
	}
			
	//confirmação da transação
	public void commitTransaction(){
		this.entityManager.getTransaction().commit();
	}
		
	//cancelar a transação
	public void rollbackTransaction(){
		 this.entityManager.getTransaction().rollback();
	}		
}
