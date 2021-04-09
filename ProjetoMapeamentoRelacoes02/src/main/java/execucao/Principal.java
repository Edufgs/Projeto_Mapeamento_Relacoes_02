package execucao;

import persistencia.PersistenciaException;
import javax.swing.JOptionPane;
import negocio.NegocioException;

/*
 * Menu onde seleciona a parte que deseja
 */
public class Principal {
	public static void main(String args[]) throws Exception {
        try {
            menuPrincipal();
        } catch (PersistenciaException e) {
            System.out.println("Erro de Persistencia: "+ e.toString());
            menuPrincipal();
        } catch (NegocioException e) {
			System.out.println("Erro de Negocio: " + e.toString());
			menuPrincipal();
		} catch (Exception e) {
			System.out.println("Erro de Exception: " + e.toString());
			menuPrincipal();
		}
    }
	
	public static void menuPrincipal() throws Exception{
		EnumPrincipal opcao;
		do{
            opcao = exibirMenu();
            switch(opcao){
                case GRUPOPRODUTO:
                	MenuGrupoProduto.menuPrincipal();
                    break;
                case PRODUTO:
                	MenuProduto.menuPrincipal();
                    break;
                case FORNECEDOR:
                	MenuFornecedor.menuPrincipal();
                    break;
                case CLIENTE:
                	MenuCliente.menuPrincipal();
                	break;	
                case VENDEDOR:
                	MenuVendedor.menuPrincipal();
                	break;
                case VENDA:
                	MenuVenda.menuPrincipal();
                	break;
                default:
                    opcao = EnumPrincipal.SAIR;
            }
        }while(opcao != EnumPrincipal.SAIR);
        System.exit(0);
	}
	
	private static EnumPrincipal exibirMenu(){
        EnumPrincipal opcao;
        opcao = (EnumPrincipal)JOptionPane.showInputDialog(null, "Escolha uma Opção", "Menu", JOptionPane.QUESTION_MESSAGE, null, EnumPrincipal.values(), EnumPrincipal.values()[0]);
        return opcao;
    }
}
