package execucao;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JOptionPane;
import negocio.NegocioException;
import negocio.VendedorNegocio;
import persistencia.PersistenciaException;
import vo.ItemVendaVO;
import vo.VendaVO;
import vo.VendedorVO;

public class MenuVendedor {
	private static VendedorNegocio vendedorNegocio; 
	
	//menu
	public static void menuPrincipal() throws Exception{
		try{
	        vendedorNegocio =new VendedorNegocio();
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
                	listarVendedor();
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
	
	//opcao menu
	private static EnumOpcao exibirMenu(){
        EnumOpcao opcao;
        opcao = (EnumOpcao)JOptionPane.showInputDialog(null, "Escolha uma Opção", "Menu", JOptionPane.QUESTION_MESSAGE, null, EnumOpcao.values(), EnumOpcao.values()[0]);
        return opcao;
    }
	
	//insere vendedor no banco
	public static void inserir() throws NegocioException, PersistenciaException{
        vendedorNegocio.inserir(lerDados());
    }
	
	//cria um objeto vendedor
	public static VendedorVO lerDados() throws PersistenciaException{
        VendedorVO fornecedorTemp = new VendedorVO();
        return lerDados(fornecedorTemp);
    }
	
	//monta o objeto vendedor
	public static VendedorVO lerDados(VendedorVO vendedorTemp) throws PersistenciaException{	
        vendedorTemp.setNome(JOptionPane.showInputDialog("Digite o nome", vendedorTemp.getNome()));
        vendedorTemp.setCpf(JOptionPane.showInputDialog("Digite o CPF",vendedorTemp.getCpf()));
        vendedorTemp.setRg(JOptionPane.showInputDialog("Digite o RG", vendedorTemp.getRg()));
        vendedorTemp.setPerComissao(Float.parseFloat(JOptionPane.showInputDialog("Entre com um valor", vendedorTemp.getPerComissao())));
        return vendedorTemp;
    }	
	
	//imprime na tela os vendedores
	public static void listarVendedor() throws HeadlessException, PersistenciaException {
		String imprimir ="";
		List<VendedorVO> listVendedorVO = vendedorNegocio.listarVendedor();
		List<VendaVO> listVendaVO;
		List<ItemVendaVO> listItemVendaVO;
		
		for (VendedorVO vendedorVO : listVendedorVO) { 
			imprimir += "Vendedor:\nCodigo: " + vendedorVO.getCodigo() + ", Nome: " + vendedorVO.getNome()+ "\nVenda:\n";
			listVendaVO = vendedorVO.getVenda();
			float totalVenda = 0;
			float totalComissaoVenda = 0;
			
			for(VendaVO vendaVO : listVendaVO) {				
				imprimir += "Codigo: " + vendaVO.getCodigo();
				listItemVendaVO = vendaVO.getItemVenda();
				float totalComissaoCadaVenda=0;
				for(ItemVendaVO itemVendaVO : listItemVendaVO){
					totalVenda+= itemVendaVO.getPrecoVenda();
					totalComissaoCadaVenda += (vendedorVO.getPerComissao() * itemVendaVO.getPrecoVenda())/100;
					totalComissaoVenda += totalComissaoCadaVenda;
				}
				imprimir += ", Total de Comissão de cada Venda: " + totalComissaoCadaVenda;
			}
			imprimir +=", Total da venda: " + totalVenda + ", Total de comissão da venda: " + totalComissaoVenda+"\n\n";
		}
		JOptionPane.showMessageDialog(null, imprimir);
	}
}
