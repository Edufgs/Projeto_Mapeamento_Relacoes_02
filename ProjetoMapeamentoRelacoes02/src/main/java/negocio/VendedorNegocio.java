package negocio;

import java.util.List;
import persistencia.FabricaEntityManager;
import persistencia.PersistenciaException;
import persistencia.VendedorDAO;
import vo.VendedorVO;


public class VendedorNegocio {
	
	private VendedorDAO vendedorDAO;
	
	public VendedorNegocio() throws NegocioException{
		try {
			this.vendedorDAO = new VendedorDAO(FabricaEntityManager.getEntityManager());					
		}catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia - "+ex.getMessage());
		}
	}
	
	public void inserir(VendedorVO vendedorVO) throws NegocioException{
		String mensagemErro = validarDados(vendedorVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioException(mensagemErro);
        }
        try{
        	vendedorDAO.beginTransaction(); //inicia a transação
        	vendedorDAO.incluir(vendedorVO); //Insere
        	vendedorDAO.commitTransaction();//confirma a inserção
        }catch(PersistenciaException ex) {
        	vendedorDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir grupo produto - "+ex.getMessage());
		}        
	}
                	
	private String validarDados(VendedorVO vendedorVo) {
        String mensagemErro = "";

        if (vendedorVo.getNome() == null || vendedorVo.getNome().length() == 0) {
            mensagemErro += "\nNome do vendedor não pode ser vazio";
        }else if(vendedorVo.getNome().length() > 40) {
        	mensagemErro += "\nNome do vendedor não pode ser maior que 40 caractere";
        }
        
        if (vendedorVo.getRg() == null || vendedorVo.getRg().length() == 0) {
            mensagemErro += "\nRG do vendedor não pode ser vazio";
        }else if(vendedorVo.getRg().length() > 9) {
        	mensagemErro += "\nRG do vendedor não pode ser maior que 9 caractere";
        }
        
        if (vendedorVo.getCpf() == null || vendedorVo.getCpf().length() == 0) {
            mensagemErro += "\nCPF do vendedor não pode ser vazio";
        }else if(vendedorVo.getRg().length() > 11) {
        	mensagemErro += "\nCPF do vendedor não pode ser maior que 11 caractere";
        }

        return mensagemErro;
    }
	
	public List<VendedorVO> listarVendedor() throws PersistenciaException{
		return vendedorDAO.listarVendedor();
	}
}
