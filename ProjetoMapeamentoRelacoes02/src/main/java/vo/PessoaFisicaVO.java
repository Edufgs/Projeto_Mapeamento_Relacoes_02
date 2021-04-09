package vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity//Diz que essa classe vai gerar objetos a serem persistidos
@DiscriminatorValue("fisica") //valor do tipo desse objeto

public abstract class PessoaFisicaVO extends PessoaVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(length = 9) //Dis como vai ser essa coluna
	private String rg;
	@Column(length = 11) //Dis como vai ser essa coluna
	private String cpf;
	
	public PessoaFisicaVO() {
		
	}

	public PessoaFisicaVO(int codigo, String nome, String rg, String cpf) {
		super(codigo, nome);
		this.rg = rg;
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return super.toString() + "\t[rg=" + rg + ", cpf=" + cpf + "]";
	}
	
}
