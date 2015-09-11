package br.com.ifrn.personalapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.service.ExercicioService;
import br.com.ifrn.personalapp.service.PessoaService;

@RestController
public class PessoaController {

	@Autowired PessoaService pessoaService;
	@Autowired ExercicioService exercicioService;

	@RequestMapping(value = "pessoa/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Pessoa pessoa) {
		return new ModelAndView("pessoa/form");
	}

	@RequestMapping(value = "pessoa/criar", method = RequestMethod.POST)
	public ModelAndView criarPessoa(@ModelAttribute Pessoa pessoa) {
		if (pessoa.getIdPessoa() == null) {
			pessoaService.salvarPessoa(pessoa);
		} else {
			pessoaService.atualizarPessoa(pessoa);
		}
		return new ModelAndView("pessoa/listar", "pessoas",pessoaService.pessoasAtivas());
	}

	@RequestMapping(value = "pessoa/ativar", method = RequestMethod.POST)
	public ModelAndView ativarPessoa(@RequestParam("id") Long id, @RequestParam("ativo") boolean ativo) {
		
		pessoaService.ativarOuDesativar(id, ativo);
	
		return new ModelAndView("pessoa/listar", "pessoas",
				pessoaService.pessoasAtivas());
	}

	@RequestMapping(value = "pessoa/editar/{id}", method = RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("pessoa/form", "pessoa",
				pessoaService.getById(id));
	}
	@RequestMapping(value = "pessoa/listar", method = RequestMethod.GET)
	public ModelAndView listar() {
		return new ModelAndView("pessoa/listar", "pessoas",
				pessoaService.pessoasAtivas());
	}
	
	@RequestMapping(value = "pessoa/listartudo", method = RequestMethod.GET)
	public ModelAndView listarTudo() {
		return new ModelAndView("pessoa/listar", "pessoas",pessoaService.pessoas());
	}
	
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
	public Pessoa pessoaApi(HttpServletResponse response,@PathVariable("id") Long id) {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		return pessoaService.getById(id);
	}

}
