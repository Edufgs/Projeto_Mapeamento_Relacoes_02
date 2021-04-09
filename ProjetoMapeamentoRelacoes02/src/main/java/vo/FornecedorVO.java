package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
@DiscriminatorValue("fornecedor") //valor do tipo desse objeto
public class FornecedorVO extends PessoaJuridicaVO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ManyToMany(fetch = FetchType.EAGER)//Opção de muitos para muitos com estrategia de recupera a lista no mesmo instante que a entidade agregadora
	private List<ProdutoVO> produto;

	public FornecedorVO() {
		this.produto = new ArrayList<ProdutoVO>();
	}

	public FornecedorVO(int codigo, String nome, String razaoSocial, List<ProdutoVO> produto) {
		super(codigo, nome, razaoSocial);
		this.produto = produto;
	}

	public List<ProdutoVO> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoVO> produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return super.toString() + "\t[Produto= " + produto.toString() + "]\n";
	}

}
