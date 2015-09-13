package br.com.ifrn.personalapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Academia;
import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.models.Role;
import br.com.ifrn.personalapp.security.CurrentUser;
import br.com.ifrn.personalapp.service.AcademiaService;
import br.com.ifrn.personalapp.service.ExercicioService;
import br.com.ifrn.personalapp.service.PessoaService;

@RestController
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	@Autowired
	ExercicioService exercicioService;
	@Autowired
	AcademiaService academiaService;
	
	@RequestMapping(value = "pessoaAdmin/criar", method = RequestMethod.GET)
	public ModelAndView formCriarAdmin(@ModelAttribute Pessoa pessoa) {
		return new ModelAndView("pessoa/formAdmin");
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "pessoa/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Pessoa pessoa) {
		return new ModelAndView("pessoa/form");
	}

	@RequestMapping(value = "pessoa/criarAdmin", method = RequestMethod.POST)
	public ModelAndView criarPessoaAdmin(@ModelAttribute Pessoa pessoa) {

		ArrayList<Academia> academias = new ArrayList<Academia>();
		academias = (ArrayList<Academia>) academiaService.academias();
		Academia academia =	academias.get(academias.size()-1);
		
		pessoa.setAcademia(academia);
		pessoa.setRole(Role.ROLE_ADMIN);
		if (pessoa.getIdPessoa() == null) {
			pessoaService.salvarPessoa(pessoa);
		} else {
			pessoaService.atualizarPessoa(pessoa);
		}
		return new ModelAndView("base/login");
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "pessoa/criar", method = RequestMethod.POST)
	public ModelAndView criarPessoa(@ModelAttribute Pessoa pessoa) {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		pessoa.setRole(Role.ROLE_USER);
		
		Academia academia = new Academia();
		academia = academiaService.getById(currentUser.getId());
		pessoa.setAcademia(academia);
		
		if (pessoa.getIdPessoa() == null) {
			pessoaService.salvarPessoa(pessoa);
		} else {
			pessoaService.atualizarPessoa(pessoa);
		}
		return new ModelAndView("pessoa/listar", "pessoas", pessoaService.pessoasPorAcademiaAtivas(currentUser.getId()));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "pessoa/ativar/{id}")
	public ModelAndView ativarPessoa(@PathVariable Long id) {
		pessoaService.ativarOuDesativar(id);
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ModelAndView("pessoa/listar", "pessoas", pessoaService.pessoasPorAcademiaAtivas(currentUser.getId()));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "pessoa/editar/{id}", method = RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("pessoa/form", "pessoa",
				pessoaService.getById(id));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "pessoa/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new ModelAndView("pessoa/listar", "pessoas",pessoaService.pessoasPorAcademiaAtivas(currentUser.getId()));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "pessoa/listarPorAcademiaAtivas/{id}", method = RequestMethod.GET)
	public ModelAndView listarPorAcademia(@PathVariable("id") Long id) {
		return new ModelAndView("pessoa/listar", "pessoas",pessoaService.pessoasPorAcademiaAtivas(id));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "pessoa/listartudo", method = RequestMethod.GET)
	public ModelAndView listarTudo() {
		return new ModelAndView("pessoa/listar", "pessoas",
				pessoaService.pessoas());
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "informacoes/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView informacoesPessoais(@PathVariable("id") Long id) {
		return new ModelAndView("pessoa/informacoes", "pessoa",pessoaService.getById(id));
	}

	// API Rest
	@RequestMapping(value = "api/pessoas", method = RequestMethod.GET)
	public List<Pessoa> pessoasApi() {
		return pessoaService.pessoas();
	}

	@RequestMapping(value = "api/pessoa/{id}", method = RequestMethod.GET)
	public Pessoa pessoaApi(HttpServletResponse response,
			@PathVariable("id") Long id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"GET,PUT,POST,DELETE");
		return pessoaService.getById(id);
	}

}
