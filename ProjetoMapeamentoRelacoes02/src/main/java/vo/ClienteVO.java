package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
@DiscriminatorValue("cliente") //valor do tipo desse objeto

public class ClienteVO extends PessoaFisicaVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private float limiteCredito;
	
	@OneToMany(fetch = FetchType.LAZY)//Opção de um para muitos onde 1 guarda uma lista de muitos, com estrategia de recupera a lista de instâncias relacionadas somente quando é solicitado
	private List<VendaVO> venda;
	
	public ClienteVO() {
		this.venda = new ArrayList<VendaVO>();
	}

	public ClienteVO(int codigo, String nome, String rg, String cpf, float limiteCredito, List<VendaVO> venda) {
		super(codigo, nome, rg, cpf);
		this.limiteCredito = limiteCredito;
		this.venda = venda;
	}

	public float getLimiteCredito(){
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public List<VendaVO> getVenda() {
		return venda;
	}

	public void setVenda(List<VendaVO> venda) {
		this.venda = venda;
	}

	@Override
	public String toString() {
		return super.toString()+ "\t[Limite Credito= " + limiteCredito + ", Venda= " + venda +"]\n";
	}
}
