package br.com.ifrn.personalapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Medidas {
	
	@Id @GeneratedValue
	@Column(name="ID_MEDIDAS")
	private Long idMedidas;
	
	@Column(name="ALTURA")
	private float altura;

	@Column(name="PESO")
	private float peso;
	
	@Column(name="BICEPS_DIREITO")
	private float bicepsDireito;
	
	@Column(name="BICEPS_ESQUERDO")
	private float bicepsEsquerdo;
	
	@Column(name="TRICEPS_DIREITO")
	private float tricepsDireito;
	
	@Column(name="TRICEPS_ESQUERDO")
	private float tricepsEsquerdo;
	
	@Column(name="PEITORAL")
	private float peitoral;
	
	@Column(name="ABDOMEN")
	private float abdomen;
	
	@Column(name="QUADRIL")
	private float quadril;
	
	@Column(name="COXA_DIREITA")
	private float coxaDireita;
	
	@Column(name="COXA_ESQUERDA")
	private float coxaEsquerda;
	
	@Column(name="GLUTEO")
	private float gluteo;
	
	@Column(name="PANTURRILHA_DIREITA")
	private float panturrilhaDireita;
	
	@Column(name="PANTURRILHA_ESQUERDA")
	private float panturrilhaEsquerda;
		
	@ManyToOne
	@JoinColumn(name="PESSOA_ID_PESSOA")
	private Pessoa pessoa;
	
	public Medidas() {
		pessoa = new Pessoa();
	}

	public Long getIdMedidas() {
		return idMedidas;
	}

	public void setIdMedidas(Long idMedidas) {
		this.idMedidas = idMedidas;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getBicepsDireito() {
		return bicepsDireito;
	}

	public void setBicepsDireito(float bicepsDireito) {
		this.bicepsDireito = bicepsDireito;
	}

	public float getBicepsEsquerdo() {
		return bicepsEsquerdo;
	}

	public void setBicepsEsquerdo(float bicepsEsquerdo) {
		this.bicepsEsquerdo = bicepsEsquerdo;
	}

	public float getTricepsDireito() {
		return tricepsDireito;
	}

	public void setTricepsDireito(float tricepsDireito) {
		this.tricepsDireito = tricepsDireito;
	}

	public float getTricepsEsquerdo() {
		return tricepsEsquerdo;
	}

	public void setTricepsEsquerdo(float tricepsEsquerdo) {
		this.tricepsEsquerdo = tricepsEsquerdo;
	}

	public float getPeitoral() {
		return peitoral;
	}

	public void setPeitoral(float peitoral) {
		this.peitoral = peitoral;
	}

	public float getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(float abdomen) {
		this.abdomen = abdomen;
	}

	public float getQuadril() {
		return quadril;
	}

	public void setQuadril(float quadril) {
		this.quadril = quadril;
	}

	public float getCoxaDireita() {
		return coxaDireita;
	}

	public void setCoxaDireita(float coxaDireita) {
		this.coxaDireita = coxaDireita;
	}

	public float getCoxaEsquerda() {
		return coxaEsquerda;
	}

	public void setCoxaEsquerda(float coxaEsquerda) {
		this.coxaEsquerda = coxaEsquerda;
	}

	public float getGluteo() {
		return gluteo;
	}

	public void setGluteo(float gluteo) {
		this.gluteo = gluteo;
	}

	public float getPanturrilhaDireita() {
		return panturrilhaDireita;
	}

	public void setPanturrilhaDireita(float panturrilhaDireita) {
		this.panturrilhaDireita = panturrilhaDireita;
	}

	public float getPanturrilhaEsquerda() {
		return panturrilhaEsquerda;
	}

	public void setPanturrilhaEsquerda(float panturrilhaEsquerda) {
		this.panturrilhaEsquerda = panturrilhaEsquerda;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
