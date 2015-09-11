package br.com.ifrn.personalapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ifrn.personalapp.models.Medidas;

@Repository
public interface MedidasDAO extends JpaRepository<Medidas, Long> {
	  
	public Medidas findByIdMedidas(Long Id);
}
