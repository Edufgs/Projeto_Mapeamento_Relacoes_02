package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.VendedorVO;

public class VendedorDAO extends DAO<VendedorVO> {

	public VendedorDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}
	
	public List<VendedorVO> listarVendedor() throws PersistenciaException{
		List<VendedorVO> listaVendedor;
		try {
			super.beginTransaction();
			Query query = entityManager.createQuery("SELECT a FROM VendedorVO a ORDER BY a.nome");
			listaVendedor = query.getResultList();
			super.commitTransaction();
		}catch(Exception e){
			throw new PersistenciaException("Erro ao Listar Vendedor: " + e.getMessage());
		}
		return listaVendedor;
	}

}
