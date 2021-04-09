package negocio;

import java.util.List;

import persistencia.ClienteDAO;
import persistencia.FabricaEntityManager;
import persistencia.PersistenciaException;
import vo.ClienteVO;

public class ClienteNegocio {
private ClienteDAO clienteDAO;
	
	public ClienteNegocio() throws Exception{
		try {
			this.clienteDAO = new ClienteDAO(FabricaEntityManager.getEntityManager());					
		}catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia - "+ex.getMessage());
		}
	}
	
	public void inserir(ClienteVO clienteVO) throws NegocioException{
		String mensagemErro = validarDados(clienteVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioException(mensagemErro);
        }
        
        try{
        	clienteDAO.beginTransaction(); //inicia a transação
        	clienteDAO.incluir(clienteVO); //Insere
        	clienteDAO.commitTransaction();//confirma a inserção
        }catch(PersistenciaException ex) {
        	clienteDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir grupo produto - "+ex.getMessage());
		}        
	}
	
	private String validarDados(ClienteVO clienteVo){
        String mensagemErro = "";

        if (clienteVo.getNome() == null || clienteVo.getNome().length() == 0) {
            mensagemErro += "\nNome do cliente não pode ser vazio";
        }else if(clienteVo.getNome().length() > 40) {
        	mensagemErro += "\nNome do cliente não pode ser maior que 40 caractere";
        }
        
        if (clienteVo.getRg() == null || clienteVo.getRg().length() == 0) {
            mensagemErro += "\nRG do cliente não pode ser vazio";
        }else if(clienteVo.getRg().length() > 9) {
        	mensagemErro += "\nRG do cliente não pode ser maior que 9 caractere";
        }
        
        if (clienteVo.getCpf() == null || clienteVo.getCpf().length() == 0) {
            mensagemErro += "\nCPF do cliente não pode ser vazio";
        }else if(clienteVo.getRg().length() > 11) {
        	mensagemErro += "\nCPF do cliente não pode ser maior que 11 caractere";
        }
      
        return mensagemErro;
    }
	
	public List<ClienteVO> listarCliente() throws PersistenciaException{
		return clienteDAO.listarCliente();
	}
}
