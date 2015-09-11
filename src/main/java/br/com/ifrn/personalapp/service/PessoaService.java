package br.com.ifrn.personalapp.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.dao.PessoaDAO;
import br.com.ifrn.personalapp.models.Pessoa;

@Service
public class PessoaService {

	private EntityManager entityManager;
	private PessoaDAO pessoaDAO;

	@Autowired
	public PessoaService(EntityManager entityManager, PessoaDAO pessoaDAO) {
		this.entityManager = entityManager;
		this.pessoaDAO = pessoaDAO;
	}
	
	public Pessoa getById(Long id) {
		return pessoaDAO.findOne(id);
	}
	
	public Pessoa getByNome(String nome) {
		return pessoaDAO.findByNome(nome);
	}
	
	public Pessoa salvarPessoa(Pessoa pessoa) {
		return pessoaDAO.save(pessoa);
	}		
	
	@Transactional
	public Pessoa atualizarPessoa(Pessoa pessoa) {
		return entityManager.merge(pessoa);
	}
	
	public void ativarOuDesativar(Long id, boolean ativo) {
		Pessoa p = pessoaDAO.getOne(id);
		p.setActive(ativo);
		pessoaDAO.save(p);
	}
	
	public List<Pessoa> pessoas() {
		return pessoaDAO.findAll();
	}
	
	public List<Pessoa> pessoasAtivas() {
		return pessoaDAO.findByActive(true);
	}
}
