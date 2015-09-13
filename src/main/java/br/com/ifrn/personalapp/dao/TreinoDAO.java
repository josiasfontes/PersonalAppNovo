package br.com.ifrn.personalapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.models.Treino;

@Repository
public interface TreinoDAO extends JpaRepository<Treino, Long> {

	//public Treino findByidTreino(Long id);
	
	public List<Treino> findBypessoaIdPessoa(Long id);
	
	public List<Treino> findBypessoaIdPessoaAndActive(Long id, boolean ativo);
	
	public List<Treino> findByActive(boolean ativo);
	
}
