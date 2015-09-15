package br.com.ifrn.personalapp.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Treino {
	
	@Id @GeneratedValue
	@Column(name="ID_TREINO")
	private Long idTreino;
	
	@Column(name = "ACTIVE")
	private boolean active = true;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DATA_INICIO")
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	
	@Column(name="DATA_FIM")
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	
	@Column(name="OBSERVACAO")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="PESSOA_ID_PESSOA")
	private Pessoa pessoa;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name="TREINO_HAS_EXERCICIO",
			joinColumns = @JoinColumn(name = "ID_TREINO"),
			inverseJoinColumns = @JoinColumn(name= "ID_EXERCICIO"))
	private Set<Exercicio> exercicios = new HashSet<Exercicio>();  
																																																																																																																																																							
	public Treino() {
		pessoa = new Pessoa();
		setDataInicio(new Date());
	}

	public Long getIdTreino() {
		return idTreino;
	}

	public void setIdTreino(Long idTreino) {
		this.idTreino = idTreino;
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

	public Date getDataInicio() {
		return dataInicio;
	}


	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	public Date getDataFim() {
		return dataFim;
	}


	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	public String getObservacao() {
		return observacao;
	}


	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Set<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(Set<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

}
