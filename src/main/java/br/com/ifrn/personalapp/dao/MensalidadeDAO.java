package br.com.ifrn.personalapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Mensalidade;

@Repository
public interface MensalidadeDAO extends JpaRepository<Mensalidade, Long>{
	
	public List<Mensalidade> findBypessoaIdPessoa(Long id);
	
	public Mensalidade findByStatus(String status);

}
