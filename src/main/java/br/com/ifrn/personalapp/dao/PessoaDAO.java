package br.com.ifrn.personalapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.models.Role;

@Repository
public interface PessoaDAO extends JpaRepository<Pessoa, Long>{
	
	public Pessoa findByNome(String nome);
	
	public List<Pessoa> findByActiveAndRole(boolean ativo, Role role);
	
	public Pessoa findByEmail(String email);
	
	//public List<Pessoa> findBypessoaIdPessoaAndActive  (Long id, boolean ativo);
	
	public List<Pessoa> findByAcademiaIdAcademiaAndActiveAndRole(Long id, boolean ativo, Role role);	
	

}
