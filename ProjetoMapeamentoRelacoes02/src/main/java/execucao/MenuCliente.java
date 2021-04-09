package execucao;

import java.awt.HeadlessException;
import java.util.List;

import javax.swing.JOptionPane;

import negocio.ClienteNegocio;
import negocio.NegocioException;
import persistencia.PersistenciaException;
import vo.ClienteVO;
import vo.FornecedorVO;
import vo.ProdutoVO;
import vo.VendaVO;

public class MenuCliente {
	
	private static ClienteNegocio clienteNegocio; 
	
	//Menu
	public static void menuPrincipal() throws Exception{
		try{
	        clienteNegocio =new ClienteNegocio();
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
                	listarCliente();
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
	
	//inserir cliente
	public static void inserir() throws NegocioException, PersistenciaException{
        clienteNegocio.inserir(lerDados());
    }
	
	//cria um objeto de cliente
	public static ClienteVO lerDados() throws PersistenciaException{
        ClienteVO fornecedorTemp = new ClienteVO();
        return lerDados(fornecedorTemp);
    }
	
	//monta o objeto cliente
	public static ClienteVO lerDados(ClienteVO clienteTemp) throws PersistenciaException{	
        clienteTemp.setNome(JOptionPane.showInputDialog("Digite o nome", clienteTemp.getNome()));
        clienteTemp.setCpf(JOptionPane.showInputDialog("Digite o CPF",clienteTemp.getCpf()));
        clienteTemp.setRg(JOptionPane.showInputDialog("Digite o RG", clienteTemp.getRg()));
        clienteTemp.setLimiteCredito(Float.parseFloat(JOptionPane.showInputDialog("Entre com um valor", clienteTemp.getLimiteCredito())));        
        return clienteTemp;
    }
	
	//imprime na tela	
	public static void listarCliente() throws PersistenciaException {
		String imprimir ="";
		List<ClienteVO> listClienteVO = clienteNegocio.listarCliente();
		List<VendaVO> listVendaVO;
		
		for (ClienteVO clienteVO : listClienteVO) { 
			imprimir += "Cliente:\nCodigo: " + clienteVO.getCodigo() + ", Nome: " + clienteVO.getNome();
			listVendaVO = clienteVO.getVenda();
						
			imprimir += ", Quantidade de venda: " + listVendaVO.toString();			
			imprimir +="\n\n";
		}
		JOptionPane.showMessageDialog(null, imprimir);
	}
}
