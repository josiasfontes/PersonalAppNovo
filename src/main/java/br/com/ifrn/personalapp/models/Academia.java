package br.com.ifrn.personalapp.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Academia {
	
	@Id @GeneratedValue
	@Column(name="ID_ACADEMIA")
	private Long idAcademia;
	
	@Column(name = "ACTIVE")
	private boolean active = true;
	
	@Column(name="RAZAO_SOCIAL", nullable = false)
	private String razaosocial;

	@Column(name="DATA")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;
	
	@Column(name="CNPJ", nullable = false)
	private int cnpj;
	
	@Column(name="NOME_RESPONSAVEL", nullable = false)
	private String nomeresponsavel;
	
	@Column(name="LOGIN", nullable = false)
	private String login;
	
	@Column(name="SENHA", nullable = false)
	private String senha;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="ENDERECO_ID_ENDERECO")
	private Endereco endereco;

	
	public Academia() {
		endereco = new Endereco();
		setDataCadastro(new Date());
	}

	public Long getIdAcademia() {
		return idAcademia;
	}

	public void setIdAcademia(Long idAcademia) {
		this.idAcademia = idAcademia;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeresponsavel() {
		return nomeresponsavel;
	}

	public void setNomeresponsavel(String nomeresponsavel) {
		this.nomeresponsavel = nomeresponsavel;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
