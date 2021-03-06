package br.com.ifrn.personalapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Endereco {
	
	@Id	@GeneratedValue
	@Column(name="ID_ENDERECO")
	private Long idEndereco;
	
	@Column(name="CEP", nullable = false)
	private String cep;
	
	@Column(name="LOGRADOURO", nullable = false)
	private String logradouro;
	
	@Column(name="BAIRRO", nullable = false)
	public String bairro;
	
	@Column(name="NUMERO", nullable = false)
	private int numero;
	
	@Column(name="COMPLEMENTO", nullable = false)
	private String complemento;
	
	@Column(name="CIDADE", nullable = false)
	private String cidade;
	
	@Column(name="ESTADO", nullable = false)
	private String estado;

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}	
}
