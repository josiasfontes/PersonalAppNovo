package br.com.ifrn.personalapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Endereco;

@Repository
public interface EnderecoDAO extends JpaRepository<Endereco, Long>{
	
	public Endereco findByCidade(String cidade);

}
