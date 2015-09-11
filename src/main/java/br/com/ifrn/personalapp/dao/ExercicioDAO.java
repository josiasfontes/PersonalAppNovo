package br.com.ifrn.personalapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Exercicio;

@Repository
public interface ExercicioDAO extends JpaRepository<Exercicio, Long>{
	
	public Exercicio findByNome(String nome);
	
	@Query(value = "select exercicio.nome from exercicio"
			+ "inner join treino_has_exercicio "
			+ "on exercicio.id_exercicio = treino_has_exercicio.id_exercicio"
			+ "inner join treino"
			+ "on treino_has_exercicio.id_treino = treino.id_treino"
			+ "where treino.id_treino = ?1", nativeQuery = true)
	public List<Exercicio> findByExercicioIdAndTreinoHasExercicioAndTreino(Long id);

}
