package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
//Define o nome da tabela, se não a tabela vai ser do mesmo nome da classe (cria se não existir)
//Tambem pode colocar o mesmo nome de uma tabela que ja existe no bando de dados
@Table (name = "produto")
public class ProdutoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id //o valor do campo codigo é valor da chave primaria la no banco (É codigo pq vem depois dessa definição)
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //Diz que o valor da chave primaria vai ser gerado automaticamente pelo banco(Tipo SEQUENCE)
	private int codigo;
	
	@Column(length = 40, nullable = false) //Dis como vai ser essa coluna
	private String nome;
	
	@Column(nullable = false) //Dis como vai ser essa coluna
	private float precoVenda;
	
	@ManyToMany(fetch = FetchType.EAGER)//Opção de um para muitos onde 1 guarda uma lista de muitos, com estrategia de recupera a lista no mesmo instante que a entidade agregadora
	@JoinTable (name = "produto_fornecedor" ,joinColumns = {@JoinColumn (name = "produto_codigo")} ,inverseJoinColumns = {@JoinColumn (name = "fornecedor_codigo")})//Adiciona uma tabela para juntar mundando o nome da coluna
	private List<FornecedorVO> fornecedor;
	
	@ManyToOne(fetch = FetchType.LAZY)//Opção de muitos para 1 onde muitos guarda um, com estrategia de recupera a lista de instâncias relacionadas somente quando é solicitado
	private GrupoProdutoVO grupoProduto;

	public ProdutoVO() {
		this.fornecedor = new ArrayList<FornecedorVO>();
	}

	public ProdutoVO(int codigo, String nome, float precoVenda, List<FornecedorVO> fornecedor, GrupoProdutoVO grupoProduto){
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.precoVenda = precoVenda;
		this.fornecedor = fornecedor;
		this.grupoProduto = grupoProduto;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getPrecoVenda() {
		return precoVenda;
	}
	
	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
	}

	public List<FornecedorVO> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<FornecedorVO> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public GrupoProdutoVO getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProdutoVO grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	@Override
	public String toString() {
		return "Produto [Codigo= " + codigo + ", Nome= " + nome + ", Preço Venda= " + precoVenda + ", Fornecedor= " + fornecedor.toString() + ", Grupo Produto= " + grupoProduto + "]\n";
	}

}
