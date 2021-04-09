package negocio;

import java.util.List;

import persistencia.FabricaEntityManager;
import persistencia.FornecedorDAO;
import persistencia.PersistenciaException;
import vo.FornecedorVO;

public class FornecedorNegocio {
		
private FornecedorDAO fornecedorDAO;
	
	public FornecedorNegocio() throws NegocioException{
		try {
			this.fornecedorDAO = new FornecedorDAO(FabricaEntityManager.getEntityManager());					
		}catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia - "+ex.getMessage());
		}
	}
	
	public void inserir(FornecedorVO fornecedorVO) throws NegocioException{
		String mensagemErro = validarDados(fornecedorVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioException(mensagemErro);
        }
        
        try{
        	fornecedorDAO.beginTransaction(); //inicia a transação
        	fornecedorDAO.incluir(fornecedorVO); //Insere
        	fornecedorDAO.commitTransaction();//confirma a inserção
        }catch(PersistenciaException ex) {
        	fornecedorDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir grupo produto - "+ex.getMessage());
		}        
	}
	
	private String validarDados(FornecedorVO fornecedorVo) {
        String mensagemErro = "";

        if (fornecedorVo.getNome() == null || fornecedorVo.getNome().length() == 0) {
            mensagemErro += "\nNome não pode ser vazio";
        }else if(fornecedorVo.getNome().length() > 40) {
        	mensagemErro += "\nNome não pode ser maior que 40 caractere";
        }
        
        if(fornecedorVo.getNomeFantasia() == null || fornecedorVo.getNomeFantasia().length() == 0) {
        	 mensagemErro += "\nNome de Fantasia não pode ser vazio";
        }else if(fornecedorVo.getNomeFantasia().length() > 40) {
        	mensagemErro += "\nNome de Fantasia não pode ser maior que 40 caractere";
        }

        return mensagemErro;
    }
	
	public List<FornecedorVO> listarFornecedor() throws PersistenciaException{
		return fornecedorDAO.listarFornecedor();
	}
}
