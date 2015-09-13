package br.com.ifrn.personalapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Exercicio;

@Repository
public interface ExercicioDAO extends JpaRepository<Exercicio, Long>{
	
	public Exercicio findByNome(String nome);
	
	public List<Exercicio> findByActive(boolean ativo);
}
