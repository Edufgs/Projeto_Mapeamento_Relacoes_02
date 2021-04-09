package execucao;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JOptionPane;

import negocio.FornecedorNegocio;
import negocio.NegocioException;
import persistencia.PersistenciaException;
import vo.FornecedorVO;
import vo.ProdutoVO;

public class MenuFornecedor {
	private static FornecedorNegocio fornecedorNegocio; 
	
	//menu
	public static void menuPrincipal() throws Exception{
		try{
	        fornecedorNegocio =new FornecedorNegocio();
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
                	listarFornecedor();
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
	
	//opcao do menu	
	private static EnumOpcao exibirMenu(){
        EnumOpcao opcao;
        opcao = (EnumOpcao)JOptionPane.showInputDialog(null, "Escolha uma Opção", "Menu", JOptionPane.QUESTION_MESSAGE, null, EnumOpcao.values(), EnumOpcao.values()[0]);
        return opcao;
    }
	
	//insere fornecedor no banco
	public static void inserir() throws NegocioException, PersistenciaException{
        fornecedorNegocio.inserir(lerDados());
    }
	
	//cria um objeto fornecedor
	public static FornecedorVO lerDados() throws PersistenciaException{
		FornecedorVO fornecedorTemp = new FornecedorVO();
        return lerDados(fornecedorTemp);
    }
	
	//monta o objeto fornecedor
	public static FornecedorVO lerDados(FornecedorVO fornecedorTemp) throws PersistenciaException{
        fornecedorTemp.setRazaoSocial((JOptionPane.showInputDialog("Digite o nome", fornecedorTemp.getRazaoSocial())));
        fornecedorTemp.setNomeFantasia((JOptionPane.showInputDialog("Digite o nome fantasia",fornecedorTemp.getNomeFantasia())));
        return fornecedorTemp;
    }
	
	//imprime fornecedor na tela
	public static void listarFornecedor() throws HeadlessException, PersistenciaException {
		String imprimir ="";
		List<FornecedorVO> listFornecedorVO = fornecedorNegocio.listarFornecedor();
		List<ProdutoVO> listProdutoVO;
		
		for (FornecedorVO fornecedorVO : listFornecedorVO) { 
			imprimir += "Fornecedor:\nCodigo: " + fornecedorVO.getCodigo() + " Nome: " + fornecedorVO.getNome() + "\nProduto:\n";
			listProdutoVO = fornecedorVO.getProduto();
			
			for(ProdutoVO produtoVO : listProdutoVO) {				
				imprimir += "Codigo: " + produtoVO.getCodigo() + " Nome: " + produtoVO.getNome()+"\n";			
			}
			imprimir +="\n";
		}
		JOptionPane.showMessageDialog(null, imprimir);
	}
		
}
