package execucao;

import javax.swing.JOptionPane;
import negocio.GrupoProdutoNegocio;
import negocio.NegocioException;
import persistencia.PersistenciaException;
import vo.GrupoProdutoVO;

public class MenuGrupoProduto {
	private static GrupoProdutoNegocio grupoProdutoNegocio; 
	
	//menu
	public static void menuPrincipal() throws Exception{
		try{
	        grupoProdutoNegocio =new GrupoProdutoNegocio();
	    }catch(NegocioException ex){
	        System.out.println("Camada de negocio e persistencia não iniciada: " + ex);
	    }
		
        EnumOpcao opcao;
        do{
            opcao = exibirMenu();
            switch(opcao){
                case INSERIR:
                	inserir();
                    break;
                case LISTAR:
                	listarGrupoProduto();
                    break;
                case SAIR:
                	opcao = EnumOpcao.SAIR;
                	break;
                case VOLTAR:
                	Principal.menuPrincipal();
                	break;
                default:
                    opcao = EnumOpcao.SAIR;
            }
        }while(opcao != EnumOpcao.SAIR);
        System.exit(0);
	}
	
	//obcao do menu
	private static EnumOpcao exibirMenu(){
        EnumOpcao opcao;
        opcao = (EnumOpcao)JOptionPane.showInputDialog(null, "Escolha uma Opção", "Menu", JOptionPane.QUESTION_MESSAGE, null, EnumOpcao.values(), EnumOpcao.values()[0]);
        return opcao;
    }
	
	//insere grupoProduto
	public static void inserir() throws NegocioException, PersistenciaException{
        grupoProdutoNegocio.inserir(lerDados());
    }
	
	//cria um objeto de grupoProduto
	public static GrupoProdutoVO lerDados() throws PersistenciaException{
		GrupoProdutoVO grupoProdutoTemp = new GrupoProdutoVO();
        return lerDados(grupoProdutoTemp);
    }
	
	//monta o objeto do grupoProduto
	public static GrupoProdutoVO lerDados(GrupoProdutoVO GrupoProdutoTemp) throws PersistenciaException{	
        GrupoProdutoTemp.setNome(JOptionPane.showInputDialog("Digite o nome", GrupoProdutoTemp.getNome()));       
        return GrupoProdutoTemp;
    }	
	
	//imprime na tela
	public static void listarGrupoProduto() throws PersistenciaException {
		JOptionPane.showMessageDialog(null, grupoProdutoNegocio.listarGrupoProduto().toString());
	}
}
