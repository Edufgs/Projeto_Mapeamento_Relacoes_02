package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.FornecedorVO;
import vo.GrupoProdutoVO;
import vo.ProdutoVO;

public class ProdutoDAO extends DAO<ProdutoVO> {

	public ProdutoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}
	
	public List<ProdutoVO> listarProduto() throws PersistenciaException{
		List<ProdutoVO> listaProduto;
		try {
			super.beginTransaction();
			Query query = entityManager.createQuery("SELECT a FROM ProdutoVO a ORDER BY a.nome");
			listaProduto = query.getResultList();
			super.commitTransaction();
		}catch(Exception e){
			throw new PersistenciaException("Erro ao Listar Produto: " + e.getMessage());
		}
		return listaProduto;
	}
	
	public List<FornecedorVO> listarFornecedor() throws PersistenciaException{
		FornecedorDAO fornecedorDAO = new FornecedorDAO(FabricaEntityManager.getEntityManager());
		return fornecedorDAO.listarFornecedor();
	}
	
	public List<GrupoProdutoVO> listarGrupoProduto() throws PersistenciaException{
		GrupoProdutoDAO grupoProdutoDAO = new GrupoProdutoDAO(FabricaEntityManager.getEntityManager());
		return grupoProdutoDAO.ListarGrupoProduto();
	}

}
