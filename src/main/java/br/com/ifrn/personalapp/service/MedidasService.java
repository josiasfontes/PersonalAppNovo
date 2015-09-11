package br.com.ifrn.personalapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.dao.MedidasDAO;
import br.com.ifrn.personalapp.models.Medidas;


@Service
public class MedidasService {

	private EntityManager entityManager;
	private MedidasDAO medidasDAO;

	@Autowired
	public MedidasService(EntityManager entityManager, MedidasDAO medidasDAO) {
		this.entityManager = entityManager;
		this.medidasDAO = medidasDAO;
	}
	
	public Medidas getById(Long id) {
		return medidasDAO.findOne(id);
	}
	
	public Medidas salvarMedidas(Medidas medidas) {
		return medidasDAO.save(medidas);
	}	
	
	public void removerMedidas(Long id) {
		medidasDAO.delete(id);
	}
	
	public Medidas atualizarMedidas(Medidas medidas) {
		return entityManager.merge(medidas);
	}
	
	public List<Medidas> medidas() {
		return medidasDAO.findAll();
	}
}
