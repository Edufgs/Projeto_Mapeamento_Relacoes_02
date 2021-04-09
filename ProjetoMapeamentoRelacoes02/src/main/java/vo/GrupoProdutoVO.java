package vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
//Define o nome da tabela, se não a tabela vai ser do mesmo nome da classe (cria se não existir)
//Tambem pode colocar o mesmo nome de uma tabela que ja existe no bando de dados
@Table (name = "grupoproduto")

public class GrupoProdutoVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id //o valor do campo codigo é valor da chave primaria la no banco (É codigo pq vem depois dessa definição)
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //Diz que o valor da chave primaria vai ser gerado automaticamente pelo banco(Tipo SEQUENCE)
	private int codigo;
	
	@Column(length = 40, nullable = false) //Dis como vai ser essa coluna
	private String nome;
	
	public GrupoProdutoVO() {
		
	}
	public GrupoProdutoVO(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
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
	@Override
	public String toString() {
		return "Grupo Produto [Codigo= " + codigo + ", Nome= " + nome + "]\n";
	}
	
}
