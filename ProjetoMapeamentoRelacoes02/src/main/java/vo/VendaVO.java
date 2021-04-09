package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
//Define o nome da tabela, se não a tabela vai ser do mesmo nome da classe (cria se não existir)
//Tambem pode colocar o mesmo nome de uma tabela que ja existe no bando de dados
@Table (name = "venda")
public class VendaVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id //o valor do campo codigo é valor da chave primaria la no banco (É codigo pq vem depois dessa definição)
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //Diz que o valor da chave primaria vai ser gerado automaticamente pelo banco(Tipo SEQUENCE)
	private int codigo;
	
	@Column(nullable = false) //Dis como vai ser essa coluna
	private Date dataVenda;
	
	@ManyToOne(fetch = FetchType.EAGER) //Opção de muitos para 1 onde muitos guarda um, com estrategia de recupera a lista no mesmo instante que a entidade agregadorao
	private VendedorVO vendedor;
	
	@ManyToOne(fetch = FetchType.EAGER)//Opção de muitos para 1 onde muitos guarda um, com estrategia de recupera a lista no mesmo instante que a entidade agregadora
	private ClienteVO cliente;
	
	@OneToMany(fetch = FetchType.LAZY)//Opção de um para muitos onde 1 guarda uma lista de muitos, com estrategia de recupera a lista de instâncias relacionadas somente quando é solicitado
	private List<ItemVendaVO> itemVenda;
	
	public VendaVO() {
		this.itemVenda = new ArrayList<ItemVendaVO>();
		
	}
	
	public VendaVO(int codigo, Date dataVenda, VendedorVO vendedor, ClienteVO cliente, List<ItemVendaVO> itemVenda) {
		super();
		this.codigo = codigo;
		this.dataVenda = dataVenda;
		this.vendedor = vendedor;
		this.cliente = cliente;
		this.itemVenda = itemVenda;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public VendedorVO getVendedor() {
		return vendedor;
	}

	public void setVendedor(VendedorVO vendedor) {
		this.vendedor = vendedor;
	}

	public List<ItemVendaVO> getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(List<ItemVendaVO> itemVenda) {
		this.itemVenda = itemVenda;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", dataVenda=" + dataVenda + ", vendedor=" + vendedor + ", cliente="
				+ cliente + ", itemVenda=" + itemVenda + "]\n";
	}
}
