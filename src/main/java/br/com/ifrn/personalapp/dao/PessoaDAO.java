package br.com.ifrn.personalapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Pessoa;

@Repository
public interface PessoaDAO extends JpaRepository<Pessoa, Long>{
	
	public Pessoa findByNome(String nome);
	
	public List<Pessoa> findByActive(boolean ativo);

}
