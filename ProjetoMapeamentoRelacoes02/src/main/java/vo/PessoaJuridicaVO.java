package vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
@DiscriminatorValue("juridica") //valor do tipo desse objeto

public abstract class PessoaJuridicaVO extends PessoaVO implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Column(length = 40) //Dis como vai ser essa coluna
	private String razaoSocial;
	
	public PessoaJuridicaVO() {
		
	}

	public PessoaJuridicaVO(int codigo, String nome, String razaoSocial) {
		super(codigo, nome);
		this.razaoSocial = razaoSocial;
	}
	
	public String getNomeFantasia() {
		return super.getNome();
	}

	public void setNomeFantasia(String nome) {
		super.setNome(nome);
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Override
	public String toString() {
		return super.toString() + "\t[Razao Social= " + razaoSocial + "]";
	}
	
}
