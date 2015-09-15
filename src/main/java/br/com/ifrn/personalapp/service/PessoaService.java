package br.com.ifrn.personalapp.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.dao.PessoaDAO;
import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.models.Role;

@Service
public class PessoaService {

	private EntityManager entityManager;
	private PessoaDAO pessoaDAO;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public PessoaService(EntityManager entityManager, PessoaDAO pessoaDAO, PasswordEncoder passwordEncoder) {
		this.entityManager = entityManager;
		this.pessoaDAO = pessoaDAO;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Pessoa getById(Long id) {
		return pessoaDAO.findOne(id);
	}
	
	public Pessoa getByNome(String nome) {
		return pessoaDAO.findByNome(nome);
	}
	
	public Pessoa getByEmail(String email) {
		return pessoaDAO.findByEmail(email);
	}
	
	public Pessoa salvarPessoa(Pessoa pessoa) {
		pessoa.setSenha(encodePassword(pessoa.getSenha()));
		return pessoaDAO.save(pessoa);
	}		
	
	@Transactional
	public Pessoa atualizarPessoa(Pessoa pessoa) {
		return entityManager.merge(pessoa);
	}
	
	private String encodePassword(String senha) {
		return passwordEncoder.encode(senha);
	}
	
	@Transactional
	public void ativarOuDesativar(Long id) {
		Pessoa p = pessoaDAO.getOne(id);
		p.setActive(false);
		pessoaDAO.save(p);
	}
	
	//getPessoaPeloId
	public Pessoa pessoasPorId(Long id) {
		return pessoaDAO.findByIdPessoa(id);
	}
	
	public List<Pessoa> pessoas() {
		return pessoaDAO.findAll();
	}
	
	public List<Pessoa> pessoasAtivas() {
		return pessoaDAO.findByActiveAndRole(true, Role.ROLE_USER);
	}
	
	public List<Pessoa> pessoasPorAcademiaAtivas(Long id) {
		return pessoaDAO.findByAcademiaIdAcademiaAndActiveAndRole(id, true, Role.ROLE_USER);
	}
}
