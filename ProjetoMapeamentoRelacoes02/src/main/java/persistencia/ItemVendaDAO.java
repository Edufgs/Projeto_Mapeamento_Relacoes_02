package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.ItemVendaVO;

public class ItemVendaDAO extends DAO<ItemVendaVO>{

	public ItemVendaDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}
	
	public List<ItemVendaVO> listarItemVenda() throws PersistenciaException{
		List<ItemVendaVO> listaItemVenda;
		try {
			super.beginTransaction();
			Query query = entityManager.createQuery("SELECT a FROM ItemVendaVO a");
			listaItemVenda = query.getResultList();
			super.commitTransaction();
		}catch(Exception e){
			throw new PersistenciaException("Erro ao Listar ItemVenda: " + e.getMessage());
		}
		return listaItemVenda;
	}

}
