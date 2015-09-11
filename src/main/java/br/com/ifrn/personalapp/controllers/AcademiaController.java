package br.com.ifrn.personalapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Academia;
import br.com.ifrn.personalapp.service.AcademiaService;
import br.com.ifrn.personalapp.service.PessoaService;

@RestController
public class AcademiaController {

	@Autowired AcademiaService academiaService;
	@Autowired PessoaService pessoaService;

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
		return new ModelAndView("pessoa/listar", "pessoas",	pessoaService.pessoas());
	}
	
	@RequestMapping(value = "academia/ativar", method = RequestMethod.POST)
	public ModelAndView ativarPessoa(@RequestParam("id") Long id, @RequestParam("ativo") boolean ativo) {
		academiaService.ativarOuDesativar(id, ativo);
		return new ModelAndView("academia/listar", "academias",
				academiaService.academiasAtivas());
	}
	
	@RequestMapping(value = "academia/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("academia/form", "academia", academiaService.getById(id));
	}
	
	@RequestMapping(value = "academia/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("academia/home");
	}
	
	@RequestMapping(value = "academia/listar", method = RequestMethod.GET) 
	public ModelAndView listar() {
		return new ModelAndView("academia/listar", "academias", 
				academiaService.academiasAtivas());
	}
	
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
