package br.com.ifrn.personalapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Medidas;
import br.com.ifrn.personalapp.service.MedidasService;

@RestController
public class MedidasController {

	@Autowired MedidasService medidasService;

	@RequestMapping(value = "medidas/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Medidas medidas) {
		return new ModelAndView("medidas/form");
	}
	
	@RequestMapping(value = "medidas/criar", method = RequestMethod.POST)
	public ModelAndView criarMedidas(@ModelAttribute Medidas medidas) {
		if (medidas.getIdMedidas() == null) {
			medidasService.salvarMedidas(medidas);
		} else {
			medidasService.atualizarMedidas(medidas);
		}
		return new ModelAndView("medidas/listar","medidas", medidasService.medidas());
	}
	
	@RequestMapping(value = "medidas/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("medidas/form", "medidas", medidasService.getById(id));
	}
	
	// API Rest
	@RequestMapping(value = "api/medidas", method = RequestMethod.GET)
	public List<Medidas> medidasApi() {
		return medidasService.medidas();
	}
	
	@RequestMapping(value = "api/medidas/{id}", method = RequestMethod.GET)
	public Medidas medidasApi(@PathVariable("id") Long id) {
		return medidasService.getById(id);
	}
	
	@RequestMapping(value = "medidas/listar", method = RequestMethod.GET) 
	public ModelAndView listar() {
		return new ModelAndView("medidas/listar", "medidas", medidasService.medidas());
	}

}
