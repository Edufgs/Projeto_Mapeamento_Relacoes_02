package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
@DiscriminatorValue("vendedor") //valor do tipo desse objeto

public class VendedorVO extends PessoaFisicaVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private float perComissao;
	
	@OneToMany(fetch = FetchType.EAGER) //Opção de um para muitos onde 1 guarda uma lista de muitos com propriedade que recupera no mesmo instante que a entidade agregadora
	private List<VendaVO> venda; 
	
	public VendedorVO() {
		this.venda = new ArrayList<VendaVO>();
	}

	public VendedorVO(int codigo, String nome, String rg, String cpf, float preComissao, List<VendaVO> venda) {
		super(codigo, nome, rg, cpf);
		this.perComissao = preComissao;
		this.venda = venda;
	}

	public float getPerComissao() {
		return perComissao;
	}

	public void setPerComissao(float preComissao) {
		this.perComissao = preComissao;
	}

	public List<VendaVO> getVenda() {
		return venda;
	}

	public void setVenda(List<VendaVO> venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return "Vendedor [percentual Comissao= " + perComissao + ", venda= " + venda + "]\n";
	}
}
