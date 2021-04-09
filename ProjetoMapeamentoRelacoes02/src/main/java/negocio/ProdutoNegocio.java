package negocio;

import java.util.List;

import persistencia.FabricaEntityManager;
import persistencia.PersistenciaException;
import persistencia.ProdutoDAO;
import vo.FornecedorVO;
import vo.GrupoProdutoVO;
import vo.ProdutoVO;

public class ProdutoNegocio {
	private ProdutoDAO produtoDAO;
	
	public ProdutoNegocio() throws NegocioException{
		try {
			this.produtoDAO = new ProdutoDAO(FabricaEntityManager.getEntityManager());					
		}catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia - "+ex.getMessage());
		}
	}
	
	public void inserir(ProdutoVO produtoVO) throws NegocioException{
		String mensagemErro = validarDados(produtoVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioException(mensagemErro);
        }
        
        try{
        	produtoDAO.beginTransaction(); //inicia a transação
        	produtoDAO.incluir(produtoVO); //Insere
        	produtoDAO.commitTransaction();//confirma a inserção
        }catch(PersistenciaException ex) {
        	produtoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir Produto - "+ex.getMessage());
		}        
	}
	
	private String validarDados(ProdutoVO produtoVo) {
        String mensagemErro = "";

        if (produtoVo.getNome() == null || produtoVo.getNome().length() == 0) {
            mensagemErro += "\nNome do Produto não pode ser vazio";
        }else if(produtoVo.getNome().length() > 40) {
        	mensagemErro += "\nNome do Produto não pode ser maior que 40 caractere";
        }
        
        if(produtoVo.getPrecoVenda() <= 0) {
        	 mensagemErro += "\nO preço de venda não pode ser menor ou igual a zero";
        }
        
        if(produtoVo.getFornecedor().isEmpty()) {
       	 mensagemErro += "\nO fornecedor não pode ser vazio";
        }
        
       if(produtoVo.getGrupoProduto() == null) {
    	   mensagemErro += "\nO fornecedor não pode ser vazio";
       }

        return mensagemErro;
    }
	
	public List<ProdutoVO> listarProduto() throws PersistenciaException{
		return produtoDAO.listarProduto();
	}
	
	public List<FornecedorVO> listarFornecedor() throws PersistenciaException{
		return produtoDAO.listarFornecedor();
	}
	
	public List<GrupoProdutoVO> listarGrupoProduto() throws PersistenciaException{
		return produtoDAO.listarGrupoProduto();
	}
}
