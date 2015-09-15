package br.com.ifrn.personalapp.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Exercicio;
import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.models.Treino;
import br.com.ifrn.personalapp.security.CurrentUser;
import br.com.ifrn.personalapp.service.ExercicioService;
import br.com.ifrn.personalapp.service.PessoaService;
import br.com.ifrn.personalapp.service.TreinoService;

@RestController
public class TreinoController {

	Pessoa pessoa = new Pessoa();
	Pessoa p = new Pessoa();
	Treino treino = new Treino();
	
	@Autowired TreinoService treinoService;
	@Autowired ExercicioService exercicioService;
	@Autowired PessoaService pessoaService;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treino/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Treino treino) {
		return new ModelAndView("treino/formCriar","exercicios", exercicioService.exercicios());
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treino/criar", method = RequestMethod.POST)
	public ModelAndView criarTreino(@ModelAttribute Treino treino) {
		treino.setPessoa(pessoa);
		if (treino.getIdTreino() == null) {		
			treinoService.salvarTreino(treino);
		}else{
			treinoService.atualizarTreino(treino);
		}
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		p = pessoaService.pessoasPorId(currentUser.getId());

		return new ModelAndView("pessoa/listar", "pessoas",
				pessoaService.pessoasPorAcademiaAtivas(p.getAcademia().getIdAcademia()));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treino/ativar/{id}")
	public ModelAndView ativarTreino(@PathVariable Long id) {
		treinoService.ativarOuDesativar(id);
		return new ModelAndView("treino/informacoes", "treinos",treinoService.treinosPessoa(pessoa.getIdPessoa()));
	}
		
	//editar
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treino/atualizar/{id}")
	public ModelAndView updateTreino(@PathVariable Treino treino, @PathVariable Long id) {
		treino.setIdTreino(id);
		treinoService.atualizarTreino(treino);
		return new ModelAndView("treino/listar","treinos", treinoService.treinos());
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treino/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		treino = treinoService.getById(id);
		Set<Exercicio> exercicios = new HashSet<Exercicio>(exercicioService.exercicios());
		treino.setExercicios(exercicios);
		return new ModelAndView("treino/formEditar", "treino", treino);
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "historicotreinos/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView historicoTreinoPessoa(@PathVariable("id") Long id) {
		return new ModelAndView("treino/historicoTreinos", "treinos",treinoService.treinosPessoa(id));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "historicotreinos/pessoa", method = RequestMethod.GET)
	public ModelAndView semHistoricoTreinoPessoa() {
		return new ModelAndView("treino/informacoes", "treinos",treinoService.treinosPessoa(pessoa.getIdPessoa()));
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treino/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView treinoPessoa(@PathVariable("id") Long id) {
		pessoa.setIdPessoa(id);
		treino = treinoService.getById(id);
		return new ModelAndView("treino/exerciciosTreino", "treino",treinoService.getById(id));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treinos/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView treinosPessoa(@PathVariable("id") Long id) {
		pessoa.setIdPessoa(id);
		return new ModelAndView("treino/informacoes", "treinos",treinoService.treinosPessoaAtivo(id));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "treinos/pessoaAtivo/{id}", method = RequestMethod.GET)
	public ModelAndView treinosPessoaAtivo(@PathVariable("id") Long id) {
		return new ModelAndView("treino/informacoes", "treinos",treinoService.treinosPessoaAtivo(id));
	}
	
	// API Rest
	@RequestMapping(value = "api/treinos", method = RequestMethod.GET)
	public List<Treino> treinosApi() {
		return treinoService.treinos();
	}
	
	@RequestMapping(value = "api/treino/{id}", method = RequestMethod.GET)
	public Treino treinoApi(@PathVariable("id") Long id) {
		return treinoService.getById(id);
	}
	
	@RequestMapping(value = "api/treinos/{id}", method = RequestMethod.GET)
	public List<Treino> treinosAtivoApi(HttpServletResponse response, @PathVariable("id") Long id) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","GET,PUT,POST,DELETE");
		return treinoService.treinosPessoaAtivo(id);
	}
	
}
