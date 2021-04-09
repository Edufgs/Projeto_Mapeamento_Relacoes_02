package vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
//Define o nome da tabela, se não a tabela vai ser do mesmo nome da classe (cria se não existir)
//Tambem pode colocar o mesmo nome de uma tabela que ja existe no bando de dados
@Table (name = "itemvenda")
public class ItemVendaVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id //o valor do campo codigo é valor da chave primaria la no banco (É codigo pq vem depois dessa definição)
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //Diz que o valor da chave primaria vai ser gerado automaticamente pelo banco(Tipo SEQUENCE)
	private int codigo;
	
	@Column(nullable = false) //Dis como vai ser essa coluna
	private int quantidade;
	
	@Column(nullable = false) //Dis como vai ser essa coluna
	private int precoVenda;
	
	private float perDesconto;
	
	@ManyToOne(fetch = FetchType.LAZY)//Opção de muitos para 1 onde muitos guarda um, com estrategia de recuperar quando for solicitado
	private ProdutoVO produto;
	
	public ItemVendaVO() {
		
	}

	public ItemVendaVO(int codigo, int quantidade, int precoVenda, float perDesconto) {
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.perDesconto = perDesconto;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getPrecoVenda() {
		return precoVenda;
	}
	
	public void setPrecoVenda(int precoVenda) {
		this.precoVenda = precoVenda;
	}
	
	public float getPerDesconto() {
		return perDesconto;
	}
	
	public void setPerDesconto(float perDesconto) {
		this.perDesconto = perDesconto;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Item Venda [codigo=" + codigo + ", quantidade=" + quantidade + ", precoVenda=" + precoVenda
				+ ", perDesconto=" + perDesconto + ", produto=" + produto + "]\n";
	}
	
}
