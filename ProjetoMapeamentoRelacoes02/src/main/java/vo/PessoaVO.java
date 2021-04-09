package vo;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
//Define o nome da tabela, se não a tabela vai ser do mesmo nome da classe (cria se não existir)
//Tambem pode colocar o mesmo nome de uma tabela que ja existe no bando de dados
@Table (name = "pessoa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Implementa a herança e diz aque a estrategia é Single Table
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)//Dados Complementares: Aqui cria uma coluna discriminador com o nome "tipo" e o tipo da informação
@DiscriminatorValue("pessoa") //O valor qe vai ser gravado na tabela em formato String que foi colocado na definição a cima

public abstract class PessoaVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id //o valor do campo codigo é valor da chave primaria la no banco (É codigo pq vem depois dessa definição)
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //Diz que o valor da chave primaria vai ser gerado automaticamente pelo banco(Tipo SEQUENCE)
	private int codigo;
	
	@Column(length = 40, nullable = false) //Dis como vai ser essa coluna
	private String nome;
	
	public PessoaVO() {

	}
	
	public PessoaVO(int codigo, String nome) {
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
		return "\n[Codigo = " + codigo + ", Nome = " + nome + "]";
	}
	
}
