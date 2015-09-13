package br.com.ifrn.personalapp.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pessoa{

	@Id	@GeneratedValue
	@Column(name = "ID_PESSOA")
	public Long idPessoa;
	
	@Column(name = "ACTIVE")
	private boolean active = true;

	@Column(name = "NOME", nullable = false)
	public String nome;

	@Column(name = "CPF", nullable = false)
	public String cpf;

	@Column(name = "SEXO", nullable = false)
	public String sexo;

	@Column(name = "IDADE", nullable = false)
	public int idade;

	@Column(name = "EMAIL", nullable = false, unique=true)
	public String email;
	
	@Column(name = "SENHA", nullable = false)
	public String senha;

	@Column(name = "TELEFONE", nullable = false)
	public int telefone;
	
	@Column(name="DATA")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACADEMIA_ID_ACADEMIA")
	private Academia academia;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ENDERECO_ID_ENDERECO")
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 20, nullable = false)
	private Role role;
	
	public Pessoa() {
		endereco = new Endereco();
		academia = new Academia();
		setDataCadastro(new Date());
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Academia getAcademia() {
		return academia;
	}

	public void setAcademia(Academia academia) {
		this.academia = academia;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
