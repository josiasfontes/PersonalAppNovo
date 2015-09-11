package br.com.ifrn.personalapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.dao.AcademiaDAO;
import br.com.ifrn.personalapp.models.Academia;


@Service
public class AcademiaService {

	private EntityManager entityManager;
	private AcademiaDAO academiaDAO;

	@Autowired
	public AcademiaService(EntityManager entityManager, AcademiaDAO academiaDAO) {
		this.entityManager = entityManager;
		this.academiaDAO = academiaDAO;
	}
	
	public Academia getById(Long id) {
		return academiaDAO.findOne(id);
	}
	
	public Academia getByRazaoSocial(String razaoSocial) {
		return academiaDAO.findByRazaosocial(razaoSocial);
	}
	
	public Academia salvarAcademia(Academia academia) {
		return academiaDAO.save(academia);
	}	
	
	public void removerAcademia(Long id) {
		academiaDAO.delete(id);
	}
	
	public Academia atualizarAcademia(Academia academia) {
		return entityManager.merge(academia);
	}
	
	public void ativarOuDesativar(Long id, boolean ativo) {
		Academia a = academiaDAO.getOne(id);
		a.setActive(ativo);
		academiaDAO.save(a);
	}
	
	public List<Academia> academias() {
		return academiaDAO.findAll();
	}
	
	public List<Academia> academiasAtivas() {
		return academiaDAO.findByActive(true);
	}
	
}
