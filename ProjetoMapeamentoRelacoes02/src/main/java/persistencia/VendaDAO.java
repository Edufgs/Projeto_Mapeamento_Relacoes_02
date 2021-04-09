package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import vo.ClienteVO;
import vo.ProdutoVO;
import vo.VendaVO;
import vo.VendedorVO;

public class VendaDAO extends DAO<VendaVO> {

	public VendaDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}
	
	public List<VendaVO> listarVenda() throws PersistenciaException{
		List<VendaVO> listaVenda;
		try {
			super.beginTransaction();
			Query query = entityManager.createQuery("SELECT a FROM VendaVO a ORDER BY a.dataVenda");
			listaVenda = query.getResultList();
			super.commitTransaction();
		}catch(Exception e){
			throw new PersistenciaException("Erro ao Listar Venda: " + e.getMessage());
		}
		return listaVenda;
	}
	
	//Lista os Vendedor para ser colocado em venda
	public List<VendedorVO> listarVendedor() throws PersistenciaException{
		VendedorDAO vendedorDAO = new VendedorDAO(FabricaEntityManager.getEntityManager());
		return vendedorDAO.listarVendedor();
	}
	
	public List<ClienteVO> listarCliente() throws PersistenciaException{
		ClienteDAO clienteDAO = new ClienteDAO(FabricaEntityManager.getEntityManager());
		return clienteDAO.listarCliente();
	}
	
	public List<ProdutoVO> listarProduto() throws PersistenciaException{
		ProdutoDAO produtoDAO = new ProdutoDAO(FabricaEntityManager.getEntityManager());
		return produtoDAO.listarProduto();
	}
	
}
