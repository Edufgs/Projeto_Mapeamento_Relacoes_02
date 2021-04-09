package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.FornecedorVO;
import vo.ProdutoVO;

public class FornecedorDAO extends DAO<FornecedorVO> {

	public FornecedorDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public List<FornecedorVO> listarFornecedor() throws PersistenciaException{
		List<FornecedorVO> listaFornecedor;
		
		try {
			super.beginTransaction();
			Query query = entityManager.createQuery("SELECT a FROM FornecedorVO a ORDER BY a.razaoSocial");
			listaFornecedor = query.getResultList();
			super.commitTransaction();
		}catch(Exception e){
			throw new PersistenciaException("Erro ao Listar Fornecedor: " + e.getMessage());
		}
		return listaFornecedor;
	}
}
