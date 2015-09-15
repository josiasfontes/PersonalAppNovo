package br.com.ifrn.personalapp.models;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Exercicio {
	
	@Id @GeneratedValue
	@Column(name="ID_EXERCICIO")
	private Long idExercicio;
	
	@Column(name="NOME", nullable = false)
	private String nome;
	
	@Column(name="SERIE")
	private String serie;
	
	@Column(name = "ACTIVE")
	private boolean active = true;
	
	@ManyToMany(mappedBy= "exercicios")
	private Set<Treino> treinos = new HashSet<Treino>();  
	
	public Long getIdExercicio() {
		return idExercicio;
	}
	public void setIdExercicio(Long idExercicio) {
		this.idExercicio = idExercicio;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
