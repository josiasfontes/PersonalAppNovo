package br.com.ifrn.personalapp.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.dao.ExercicioDAO;
import br.com.ifrn.personalapp.models.Exercicio;


@Service
public class ExercicioService {

	private EntityManager entityManager;
	private ExercicioDAO exercicioDAO;

	@Autowired
	public ExercicioService(EntityManager entityManager, ExercicioDAO exercicioDAO) {
		this.entityManager = entityManager;
		this.exercicioDAO = exercicioDAO;
	}
	
	public Exercicio getById(Long id) {
		return exercicioDAO.findOne(id);
	}

	public Exercicio salvarExercicio(Exercicio exercicio) {
		return exercicioDAO.save(exercicio);
	}	
	
	public void removerExercicio(Long id) {
		exercicioDAO.delete(id);
	}
	
	public Exercicio atualizarExercicio(Exercicio exercicio) {
		return entityManager.merge(exercicio);
	}
	
	public List<Exercicio> exercicios() {
		return exercicioDAO.findAll();
	}
	
}
