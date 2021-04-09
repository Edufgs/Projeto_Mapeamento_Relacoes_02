package execucao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

import negocio.ClienteNegocio;
import negocio.ItemVendaNegocio;
import negocio.NegocioException;
import negocio.VendaNegocio;
import negocio.VendedorNegocio;
import persistencia.PersistenciaException;
import vo.ClienteVO;
import vo.FornecedorVO;
import vo.ItemVendaVO;
import vo.ProdutoVO;
import vo.VendaVO;
import vo.VendedorVO;

public class MenuVenda {
	private static VendaNegocio vendaNegocio; 
	
	//menu
	public static void menuPrincipal() throws NegocioException, Exception{
		try{
	        vendaNegocio =new VendaNegocio();
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
                	listarVenda();
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
	
	//insere venda no banco
	public static void inserir() throws NegocioException, Exception{
        vendaNegocio.inserir(lerDados());
    }
	
	//cria um objeto venda
	public static VendaVO lerDados() throws Exception{
		VendaVO VendaTemp = new VendaVO();
        return lerDados(VendaTemp);
    }
	
	//monta o objeto venda
	public static VendaVO lerDados(VendaVO vendaTemp) throws NegocioException, Exception{
		Map<String,VendedorVO> mapVendedor = convertListVendedor(vendaNegocio.listarVendedor()); //recupera vendedores para selecionar
		Map<String, ClienteVO> mapCliente = convertListCliente(vendaNegocio.listarCliente()); //recupera cliente para selecionar
		List<ItemVendaVO> listVenda = new ArrayList<ItemVendaVO>();
		
		//Verifica se tem cliente cadastrado e vendedor cadastrado
		if(mapVendedor.isEmpty()){
			JOptionPane.showMessageDialog(null, "Lista de vendedor está vazia");
			menuPrincipal();
		}else if(mapCliente.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Lista de Cliente está vazia");
			menuPrincipal();
		}
		
		vendaTemp.setDataVenda(new Date()); //insere a data
		//escolhe o vendedor
		vendaTemp.setVendedor(mapVendedor.get((String)JOptionPane.showInputDialog(null, "Escolha o Vendedor", "Vendedor", JOptionPane.QUESTION_MESSAGE, null, mapVendedor.keySet().toArray(), mapVendedor.keySet().toArray()[0])));
		//escolhe o cliente
		vendaTemp.setCliente(mapCliente.get((String)JOptionPane.showInputDialog(null, "Escolha o Cliente", "Cliente", JOptionPane.QUESTION_MESSAGE, null, mapCliente.keySet().toArray(), mapCliente.keySet().toArray()[0])));
		
		//Adiciona N vezes o item de venda  
		do{			
        	listVenda.add(montarItemVenda()); //mota item venda
        }while(JOptionPane.showConfirmDialog(null, "Deseja cadastrar mais Item Venda?", "Escolha a opção",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
        
        vendaTemp.setItemVenda(listVenda);
		
        return vendaTemp;
    }
	
	//monta item venda
	public static ItemVendaVO montarItemVenda() throws NegocioException, Exception {
		ItemVendaVO itemTemp = new ItemVendaVO();
		Map<String, ProdutoVO> mapProduto = convertListProduto(vendaNegocio.listarProduto()); //recupera produtos para ser selecionado
		ItemVendaNegocio itemVendaDAO = new ItemVendaNegocio();

		//veridifca se tem produto no Map
		if(mapProduto.isEmpty()){
			JOptionPane.showMessageDialog(null, "Lista de produto está vazia");
			menuPrincipal();
		}
		
		//Escolhe o produto
		itemTemp.setProduto(mapProduto.get((String)JOptionPane.showInputDialog(null, "Escolha o Produto", "Produto", JOptionPane.QUESTION_MESSAGE, null, mapProduto.keySet().toArray(), mapProduto.keySet().toArray()[0])));
		
		//Verifica se o produto foi escolhido
		if(itemTemp.getProduto()==null){
			JOptionPane.showMessageDialog(null, "Produto não escolhido");
			menuPrincipal();
		}
		
		itemTemp.setPerDesconto(Float.parseFloat(JOptionPane.showInputDialog("Entre com o valor percenctual de desconto", itemTemp.getPerDesconto()))); //Coloca o percentual
		itemTemp.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade",itemTemp.getQuantidade())));//Seleciona a quantidade		
		itemTemp.setPrecoVenda((int)(itemTemp.getQuantidade()*(itemTemp.getProduto().getPrecoVenda()-((itemTemp.getPerDesconto()*itemTemp.getProduto().getPrecoVenda())/100)))); //Calcula o preço com desconto
		
		itemVendaDAO.inserir(itemTemp); //insere Item Venda
		
		return itemTemp;
	}
	
	//imprime lista venda na tela
	public static void listarVenda() throws PersistenciaException {
		String imprimir ="";
		float total = 0;
		List<VendaVO> listVendaVO = vendaNegocio.listarVenda();
		List<ItemVendaVO> listItemVendaVO;
		
		for (VendaVO vendaVO : listVendaVO) { 
			imprimir += "Venda:\nCodigo: " + vendaVO.getCodigo() + ", Data: " + vendaVO.getDataVenda()+ "\nItem Venda:\n";
			listItemVendaVO = vendaVO.getItemVenda();
			for(ItemVendaVO itemVendaVO : listItemVendaVO) {				
				imprimir += "Codigo: " + itemVendaVO.getCodigo() + ", Quantidade: " + itemVendaVO.getQuantidade()+", Preço: "+ itemVendaVO.getPrecoVenda() + ", Percentual: " + itemVendaVO.getPerDesconto() + "\n";
				total+= itemVendaVO.getPrecoVenda();
			}
			imprimir +="\n";
		}
		JOptionPane.showMessageDialog(null, imprimir + "Total: " + total);
	}
	
	//Transforma a lista Vendedor em Map;
	public static Map<String, VendedorVO> convertListVendedor(List<VendedorVO> list) {
	    Map<String, VendedorVO> map = new HashMap<String, VendedorVO>();
	    for (VendedorVO vendedorVO : list){
	        map.put(vendedorVO.getNome(), vendedorVO);
	    }
	    return map;
	}
		
	//Transforma a lista Cliente em Map
	public static Map<String, ClienteVO> convertListCliente(List<ClienteVO> list) {
	   Map<String, ClienteVO> map = new HashMap<String, ClienteVO>();
	   for (ClienteVO clienteVO : list) {
	        map.put(clienteVO.getNome(), clienteVO);
	    }
	    return map;
	}
		
	//Transforma a lista Produto em Map
	public static Map<String, ProdutoVO> convertListProduto(List<ProdutoVO> list) {
	    Map<String, ProdutoVO> map = new HashMap<String, ProdutoVO>();
	    for (ProdutoVO produtoVO : list) {
		        map.put(produtoVO.getNome(), produtoVO);
	    }
	    return map;
	}
}