package br.com.ifrn.personalapp.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Academia;
import br.com.ifrn.personalapp.models.Medidas;
import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.security.CurrentUser;
import br.com.ifrn.personalapp.service.AcademiaService;
import br.com.ifrn.personalapp.service.MedidasService;
import br.com.ifrn.personalapp.service.PessoaService;

@RestController
public class MedidasController {

	Academia pessoaAcademia = new Academia();
	Pessoa p = new Pessoa();
	
	@Autowired
	MedidasService medidasService;
	@Autowired
	PessoaService pessoaService;
	@Autowired
	AcademiaService academiaService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "pessoa/medidas/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Medidas medidas) {
		return new ModelAndView("medidas/form");
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "medidas/criar", method = RequestMethod.POST)
	public ModelAndView criarMedidas(@ModelAttribute Medidas medidas) {
		
		if (medidas.getIdMedidas() == null) {
			ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
			pessoas = (ArrayList<Pessoa>) pessoaService.pessoasAtivas();
			Pessoa pessoa = pessoas.get(pessoas.size() - 1);
			medidas.setPessoa(pessoa);
			medidasService.salvarMedidas(medidas);
		} else {
			medidasService.atualizarMedidas(medidas);
		}
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		p = pessoaService.pessoasPorId(currentUser.getId());

		return new ModelAndView("pessoa/listar", "pessoas",
				pessoaService.pessoasPorAcademiaAtivas(p.getAcademia().getIdAcademia()));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "medidas/editar/{id}", method = RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("medidas/form", "medidas",medidasService.getById(id));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "medidasPessoa/listar/{id}", method = RequestMethod.GET)
	public ModelAndView medidasPessoa(@PathVariable("id") Long id) {
		return new ModelAndView("medidas/medidasPessoa", "medidas",
				medidasService.medidasPessoa(id));
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

}
