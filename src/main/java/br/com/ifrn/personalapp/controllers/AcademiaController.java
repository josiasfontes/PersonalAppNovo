package br.com.ifrn.personalapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import br.com.ifrn.personalapp.models.Academia;
import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.security.CurrentUser;
import br.com.ifrn.personalapp.service.AcademiaService;
import br.com.ifrn.personalapp.service.PessoaService;

@RestController
public class AcademiaController {
	
	@Autowired AcademiaService academiaService;
	@Autowired PessoaService pessoaService;
	
	Academia academiaTeste = new Academia();
	Academia pessoaAcademia = new Academia();
	Pessoa p = new Pessoa();
	

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homeAdmin() {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//if (academiaTeste.getIdAcademia() == null) {
		p = pessoaService.pessoasPorId(currentUser.getId());
		
		//pessoaAcademia = academiaService.getById(p.getAcademia().getIdAcademia());
		
		//pessoa.setAcademia(p.getAcademia());
			
			
			return new ModelAndView("academia/home","academia", academiaService.getById(p.getAcademia().getIdAcademia()));
		//}else{
			//return new ModelAndView("academia/home","academia", academiaService.getById(academiaTeste.getIdAcademia()));
		//}
	}
	
	@RequestMapping(value = "academia/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Academia academia) {
		return new ModelAndView("academia/form");
	}

	@RequestMapping(value = "academia/criar", method = RequestMethod.POST)
	public ModelAndView criarAcademia(@ModelAttribute Academia academia) {
		if (academia.getIdAcademia() == null) {																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																								
			academiaService.salvarAcademia(academia);
		}else{
			academiaService.atualizarAcademia(academia);
		}
		academiaTeste.setIdAcademia(academia.getIdAcademia());
		return new ModelAndView(new RedirectView("pessoaAdmin/criar"));
	}
	
	
	@RequestMapping(value = "academia/pessoaAdmin/criar", method = RequestMethod.GET)
	public ModelAndView formCriarAdmin(@ModelAttribute Pessoa pessoa) {
		return new ModelAndView("pessoa/formAdmin");
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "academia/ativar/{id}")
	public ModelAndView ativarAcademia(@PathVariable Long id) {
		academiaService.ativarOuDesativar(id);
		return new ModelAndView("academia/listar", "academias",
				academiaService.academiasAtivas());
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "academia/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("academia/form", "academia", academiaService.getById(id));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "academia/listar", method = RequestMethod.GET) 
	public ModelAndView listar() {
		return new ModelAndView("academia/listar", "academias", 
				academiaService.academiasAtivas());
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "academia/listartudo", method = RequestMethod.GET)
	public ModelAndView listarTudo() {
		return new ModelAndView("academia/listar", "academias",
				academiaService.academias());
	}
		
	// API Rest
	@RequestMapping(value = "api/academias", method = RequestMethod.GET)
	public List<Academia> academiasApi() {
		return academiaService.academias();
	}
	
	@RequestMapping(value = "api/academia/{id}", method = RequestMethod.GET)
	public Academia academiaApi(@PathVariable("id") Long id) {
		return academiaService.getById(id);
	}	
}
