package negocio;

import java.util.List;
import persistencia.FabricaEntityManager;
import persistencia.PersistenciaException;
import persistencia.VendaDAO;
import vo.ClienteVO;
import vo.ProdutoVO;
import vo.VendaVO;
import vo.VendedorVO;

public class VendaNegocio {
	
	private VendaDAO vendaDAO;
	
	public VendaNegocio() throws NegocioException{
		try {
			this.vendaDAO = new VendaDAO(FabricaEntityManager.getEntityManager());					
		}catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia - "+ex.getMessage());
		}
	}
	
	public void inserir(VendaVO vendaVO) throws NegocioException{
		String mensagemErro = validarDados(vendaVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioException(mensagemErro);
        }
        
        try{
        	vendaDAO.beginTransaction(); //inicia a transação
        	vendaDAO.incluir(vendaVO); //Insere
        	vendaDAO.commitTransaction();//confirma a inserção
        }catch(PersistenciaException ex) {
        	vendaDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir grupo produto - "+ex.getMessage());
		}        
	}
	
	private String validarDados(VendaVO vendaVO) {
        String mensagemErro = "";

        if (vendaVO.getDataVenda() == null) {
            mensagemErro += "\nData não pode ser vazio";
        }
        
        if(vendaVO.getVendedor() == null) {
        	mensagemErro += "\nO vendedor não pode ser vazio";
        }
        
        if(vendaVO.getCliente() == null) {
        	mensagemErro += "\nO cliente não pode ser vazio";
        }

        return mensagemErro;
    }
	
	public List<VendaVO> listarVenda() throws PersistenciaException{
		return vendaDAO.listarVenda();
	}
	
	public List<VendedorVO> listarVendedor() throws PersistenciaException{
		return vendaDAO.listarVendedor();
	}
	
	public List<ClienteVO> listarCliente() throws PersistenciaException{
		return vendaDAO.listarCliente();
	}
	
	public List<ProdutoVO> listarProduto() throws PersistenciaException{
		return vendaDAO.listarProduto();
	}
}
