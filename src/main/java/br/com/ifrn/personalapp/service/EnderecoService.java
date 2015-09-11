package br.com.ifrn.personalapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.dao.EnderecoDAO;
import br.com.ifrn.personalapp.models.Endereco;

@Service
public class EnderecoService {

	private EntityManager entityManager;
	private EnderecoDAO enderecoDAO;

	@Autowired
	public EnderecoService(EntityManager entityManager, EnderecoDAO enderecoDAO) {
		this.entityManager = entityManager;
		this.enderecoDAO = enderecoDAO;
	}
	
	public Endereco getById(Long id) {
		return enderecoDAO.findOne(id);
	}
	
	public Endereco getByNome(String cidade) {
		return enderecoDAO.findByCidade(cidade);
	}
	
	public Endereco salvarEndereco(Endereco endereco) {
		return enderecoDAO.save(endereco);
	}		
	
	public Endereco atualizarEndereco(Endereco endereco) {
		return entityManager.merge(endereco);
	}
	
	public List<Endereco> enderecos() {
		return enderecoDAO.findAll();
	}
}
