package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.ClienteVO;

public class ClienteDAO extends DAO<ClienteVO> {

	public ClienteDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public List<ClienteVO> listarCliente() throws PersistenciaException{
		List<ClienteVO> listaCliente;
		try {
			super.beginTransaction();
			Query query = entityManager.createQuery("SELECT a FROM ClienteVO a ORDER BY a.nome");
			listaCliente = query.getResultList();
			super.commitTransaction();
		}catch(Exception e){
			throw new PersistenciaException("Erro ao Listar Cliente: " + e.getMessage());
		}
		return listaCliente;
	}
}
