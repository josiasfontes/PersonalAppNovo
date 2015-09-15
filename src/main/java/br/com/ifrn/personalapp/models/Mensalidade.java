package br.com.ifrn.personalapp.models;

import java.util.Calendar;
import java.util.Date;

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
public class Mensalidade {

	@Id @GeneratedValue
	@Column(name="ID_MENSALIDADE")
	public Long idMensalidade;
	
	@Column(name="VALOR", nullable = false)
	public double valor;
	
	@Column(name="ULTIMO_PAGAMENTO")
	@Temporal(TemporalType.DATE)
	private Date ultimoPagamento;  

	@Column(name="DATA_VENCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Column(name="STATUS", nullable = false)
	private boolean pago = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PESSOA_ID_PESSOA")
	private Pessoa pessoa;
	
	public Mensalidade(){
		Date data = new Date();
	 	Calendar c = Calendar.getInstance();
	 	c.setTime(data);
	 	c.add(Calendar.DATE, +30);
	 	data = c.getTime();
		setDataVencimento(data);
		//setUltimoPagamento(new Date());
	}

	public Long getIdMensalidade() {
		return idMensalidade;
	}

	public void setIdMensalidade(Long idMensalidade) {
		this.idMensalidade = idMensalidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getUltimoPagamento() {
		return ultimoPagamento;
	}

	public void setUltimoPagamento(Date ultimoPagamento) {
		this.ultimoPagamento = ultimoPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}