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
	
}
