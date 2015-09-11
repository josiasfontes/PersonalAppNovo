package br.com.ifrn.personalapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.dao.MensalidadeDAO;
import br.com.ifrn.personalapp.models.Mensalidade;
import br.com.ifrn.personalapp.models.Treino;

@Service
public class MensalidadeService {

	private EntityManager entityManager;
	private MensalidadeDAO mensalidadeDAO;

	@Autowired
	public MensalidadeService(EntityManager entityManager, MensalidadeDAO mensalidadeDAO) {
		this.entityManager = entityManager;
		this.mensalidadeDAO = mensalidadeDAO;
	}
	
	public Mensalidade getById(Long id) {
		return mensalidadeDAO.findOne(id);
	}
	
	public Mensalidade getByNome(String status) {
		return mensalidadeDAO.findByStatus(status);
	}
	
	public Mensalidade salvarMensalidade(Mensalidade mensalidade) {
		return mensalidadeDAO.save(mensalidade);
	}		
	
	public Mensalidade atualizarMensalidade(Mensalidade mensalidade) {
		return entityManager.merge(mensalidade);
	}
	
	public List<Mensalidade> mensalidadePessoa(Long id) {
		return mensalidadeDAO.findBypessoaIdPessoa(id);
	}
	
	public List<Mensalidade> mensalidades() {
		return mensalidadeDAO.findAll();
	}
}
