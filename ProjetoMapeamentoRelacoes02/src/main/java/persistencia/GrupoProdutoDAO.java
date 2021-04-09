package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.GrupoProdutoVO;

public class GrupoProdutoDAO extends DAO<GrupoProdutoVO> {

	public GrupoProdutoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}
	
	public List<GrupoProdutoVO> ListarGrupoProduto() throws PersistenciaException{
		List<GrupoProdutoVO> listaGrupoProduto;
		try {
			super.beginTransaction();
			Query query = entityManager.createQuery("SELECT a FROM GrupoProdutoVO a");
			listaGrupoProduto = query.getResultList();
			super.commitTransaction();
		}catch(Exception e){
			throw new PersistenciaException("Erro ao Listar Grupo de Produto: " + e.getMessage());
		}
		return listaGrupoProduto;
	}

}
