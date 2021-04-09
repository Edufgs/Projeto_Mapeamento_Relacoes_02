package negocio;

import java.util.List;
import persistencia.FabricaEntityManager;
import persistencia.GrupoProdutoDAO;
import persistencia.PersistenciaException;
import vo.GrupoProdutoVO;

public class GrupoProdutoNegocio {
	private GrupoProdutoDAO grupoProdutoDAO;
	
	public GrupoProdutoNegocio() throws NegocioException{
		try {
			this.grupoProdutoDAO = new GrupoProdutoDAO(FabricaEntityManager.getEntityManager());					
		}catch(PersistenciaException ex) {
			throw new NegocioException("Erro ao iniciar a Persistencia - "+ex.getMessage());
		}
	}
	
	public void inserir(GrupoProdutoVO grupoProdutoVO) throws NegocioException{
		String mensagemErro = validarDados(grupoProdutoVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioException(mensagemErro);
        }
        
        try{
        	grupoProdutoDAO.beginTransaction(); //inicia a transação
        	grupoProdutoDAO.incluir(grupoProdutoVO); //Insere
        	grupoProdutoDAO.commitTransaction();//confirma a inserção
        }catch(PersistenciaException ex) {
        	grupoProdutoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir grupo produto - "+ex.getMessage());
		}        
	}
	
	private String validarDados(GrupoProdutoVO grupoProdutoVo) {
        String mensagemErro = "";

        if (grupoProdutoVo.getNome() == null || grupoProdutoVo.getNome().length() == 0) {
            mensagemErro += "\nNome do aluno não pode ser vazio";
        }else if(grupoProdutoVo.getNome().length() > 40) {
        	mensagemErro += "\nNome do aluno não pode ser maior que 40 caractere";
        }

        return mensagemErro;
    }
	
	public List<GrupoProdutoVO> listarGrupoProduto() throws PersistenciaException{
		return grupoProdutoDAO.ListarGrupoProduto();
	}
}
