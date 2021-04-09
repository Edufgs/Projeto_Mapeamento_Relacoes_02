package negocio;

import persistencia.FabricaEntityManager;
import persistencia.ItemVendaDAO;
import persistencia.PersistenciaException;
import vo.ItemVendaVO;

public class ItemVendaNegocio {
private ItemVendaDAO itemVendaDAO;
	
	public ItemVendaNegocio() throws NegocioException{
		try {
			this.itemVendaDAO = new ItemVendaDAO(FabricaEntityManager.getEntityManager());					
		}catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia - "+ex.getMessage());
		}
	}
	
	public void inserir(ItemVendaVO itemVendaVO) throws NegocioException{
		String mensagemErro = validarDados(itemVendaVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioException(mensagemErro);
        }
        
        try{
        	itemVendaDAO.beginTransaction(); //inicia a transação
        	itemVendaDAO.incluir(itemVendaVO); //Insere
        	itemVendaDAO.commitTransaction();//confirma a inserção
        }catch(PersistenciaException ex) {
        	itemVendaDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir grupo produto - "+ex.getMessage());
		}        
	}
	
	private String validarDados(ItemVendaVO itemVendaVO) {
        String mensagemErro = "";

        if (itemVendaVO.getQuantidade() <=0) {
            mensagemErro += "\nQuantidade de produto não pode ser menor ou igual a zero";
        }
        
        if(itemVendaVO.getPerDesconto() < 0) {
        	mensagemErro += "\nO vendedor não pode ser vazio";
        }
        return mensagemErro;
    }

}
