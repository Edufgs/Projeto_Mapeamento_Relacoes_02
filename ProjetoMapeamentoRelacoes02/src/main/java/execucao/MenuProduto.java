package execucao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import negocio.FornecedorNegocio;
import negocio.NegocioException;
import negocio.ProdutoNegocio;
import persistencia.PersistenciaException;
import vo.FornecedorVO;
import vo.GrupoProdutoVO;
import vo.ProdutoVO;

public class MenuProduto {
	private static ProdutoNegocio produtoNegocio; 
	
	//menu
	public static void menuPrincipal() throws Exception{
		try{
	        produtoNegocio =new ProdutoNegocio();
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
                	listarProduto();
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
	
	//insere produto no banco
	public static void inserir() throws Exception{
        produtoNegocio.inserir(lerDados());
    }
	
	//cria um objeto produto
	public static ProdutoVO lerDados() throws Exception{
		ProdutoVO produtoTemp = new ProdutoVO();
        return lerDados(produtoTemp);
    }
	
	//monta o objeto produto
	public static ProdutoVO lerDados(ProdutoVO produtoTemp) throws Exception{
		List<FornecedorVO> fornecedor = new ArrayList<FornecedorVO>();
		Map<String,FornecedorVO> mapFornecedor = convertListFornecedor(produtoNegocio.listarFornecedor()); //busca os fornecedores para selecionar
		Map<String,GrupoProdutoVO> mapGrupoProduto = convertListGrupoProduto(produtoNegocio.listarGrupoProduto()); //busca os grupos produtos para selecionar
		
		//Verifica se tem fornecedor e grupoProduto cadastrado
		if(mapFornecedor.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lista de Fornecedor está vazia");
			menuPrincipal();
		}else if(mapGrupoProduto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lista de Grupo Produto está vazia");
			menuPrincipal();
		}
		
		//Seleciona  grupoProduto
		GrupoProdutoVO grupoProdutoVO = mapGrupoProduto.get((String)JOptionPane.showInputDialog(null, "Escolha o Grupo Produto", "Prupo Produto", JOptionPane.QUESTION_MESSAGE, null, mapGrupoProduto.keySet().toArray(),mapGrupoProduto.keySet().toArray()[0]));
		produtoTemp.setGrupoProduto(grupoProdutoVO);
        produtoTemp.setNome(JOptionPane.showInputDialog("Digite o nome", produtoTemp.getNome()));
        produtoTemp.setPrecoVenda(Float.parseFloat(JOptionPane.showInputDialog("Entre com um valor", null)));	
        
        do {
        	//Escolha o fornecedor
        	FornecedorVO fornecedorVO = mapFornecedor.get((String) JOptionPane.showInputDialog(null,"Escolha o Fornecedor","Fornecedores", JOptionPane.QUESTION_MESSAGE, null, mapFornecedor.keySet().toArray(), mapFornecedor.keySet().toArray()[0]));        	        	        	
        	
        	//Verifica se ja foi inserido o fornecedor
        	boolean verif = false;
        	for(int i = 0; i<fornecedor.size(); i++) {
        		if(fornecedor.get(i).getCodigo() == fornecedorVO.getCodigo()){
        			verif = true;
                    JOptionPane.showMessageDialog(null, "Aluno ja está cadastrado nessa disciplina");
                    break;
                }
        	}
        	if(verif == false){
                fornecedor.add(fornecedorVO);
            }
        	
        }while(JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais fornecedor?", "Escolha a opção",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        produtoTemp.setFornecedor(fornecedor); //insere lista de fornecedores na lista de fornecedores do produto
        return produtoTemp;
    }
	
	//imprime os produtos na tela	
	public static void listarProduto() throws PersistenciaException {
		String imprimir ="";
		List<ProdutoVO> listProdutoVO = produtoNegocio.listarProduto();
		List<FornecedorVO> listFornecedorVO;
		
		for (ProdutoVO produtoVO : listProdutoVO) { 
			imprimir += "Produto:\nCodigo: " + produtoVO.getCodigo() + ", Nome: " + produtoVO.getNome() + ", Preço: " + produtoVO.getPrecoVenda() + ", Grupo: " + produtoVO.getGrupoProduto().getNome() + "\nFornecedores:\n";
			listFornecedorVO = produtoVO.getFornecedor();
			
			for(FornecedorVO fornecedorVO : listFornecedorVO) {				
				imprimir += "Codigo: " + fornecedorVO.getCodigo() + ", Razão Social: " + fornecedorVO.getRazaoSocial()+"\n";			
			}
			imprimir +="\n";
		}
		JOptionPane.showMessageDialog(null, imprimir);
	}
	
	//Transforma a lista Fornecedor em Map;
	public static Map<String, FornecedorVO> convertListFornecedor(List<FornecedorVO> list) {
	    Map<String, FornecedorVO> map = new HashMap<String, FornecedorVO>();
	    for (FornecedorVO fornecedorVO : list){
	        map.put(fornecedorVO.getNomeFantasia(), fornecedorVO);
	    }
	    return map;
	}
	
	//Transforma a lista GrupoProduto em Map
	public static Map<String, GrupoProdutoVO> convertListGrupoProduto(List<GrupoProdutoVO> list) {
	    Map<String, GrupoProdutoVO> map = new HashMap<String, GrupoProdutoVO>();
	    for (GrupoProdutoVO grupoProdutoVO : list) {
	        map.put(grupoProdutoVO.getNome(), grupoProdutoVO);
	    }
	    return map;
	}
}
