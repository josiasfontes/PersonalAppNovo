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

import br.com.ifrn.personalapp.models.Treino;
import br.com.ifrn.personalapp.service.ExercicioService;
import br.com.ifrn.personalapp.service.PessoaService;
import br.com.ifrn.personalapp.service.TreinoService;

@RestController
public class TreinoController {

	@Autowired TreinoService treinoService;
	@Autowired ExercicioService exercicioService;
	@Autowired PessoaService pessoaService;

	@RequestMapping(value = "treino/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Treino treino) {
		return new ModelAndView("treino/formCriar","exercicios", exercicioService.exercicios());
	}

	@RequestMapping(value = "treino/criar", method = RequestMethod.POST)
	public ModelAndView criarTreino(@ModelAttribute Treino treino) {
		if (treino.getIdTreino() == null) {		
			System.out.println("Treino: "+ treino.getExercicios().size());
			treinoService.salvarTreino(treino);
		}else{
			treinoService.atualizarTreino(treino);
		}
		return new ModelAndView("pessoa/listar", "pessoas",pessoaService.pessoasAtivas());
	}
	
	@RequestMapping(value = "treino/ativar{id}", method = RequestMethod.POST)
	public ModelAndView ativarPessoa(@RequestParam("id") Long id, @RequestParam("ativo") boolean ativo) {
		
		treinoService.ativarOuDesativar(id, ativo);

		return new ModelAndView("treino/listar", "treinos",	treinoService.treinosAtivos());
	}

	//editar
	@RequestMapping(value = "treino/atualizar/{id}")
	public ModelAndView updateTreino(@PathVariable Treino treino, @PathVariable Long id) {
		treino.setIdTreino(id);
		treinoService.atualizarTreino(treino);
		return new ModelAndView("treino/listar","treinos", treinoService.treinos());
	}
	
	//deletar
	@RequestMapping(value = "treino/deletar/{id}")
	public ModelAndView deletarTreino(@PathVariable Long id) {
		treinoService.removerTreino(id);
		return new ModelAndView("treino/listar","treinos", treinoService.treinos());
	}
	
	@RequestMapping(value = "treino/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("treino/formEditar", "treino", treinoService.getById(id));
	}
	
	@RequestMapping(value = "treino/listar", method = RequestMethod.GET) 
	public ModelAndView listar() {
		return new ModelAndView("treino/listar", "treinos", 
				treinoService.treinosAtivos());
	}
	
	@RequestMapping(value = "treino/listartudo", method = RequestMethod.GET)
	public ModelAndView listarTudo() {
		return new ModelAndView("treino/listar", "treinos",
				treinoService.treinos());
	}
	
	
	@RequestMapping(value = "historicotreinos/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView historicoTreinoPessoa(@PathVariable("id") Long id) {
		return new ModelAndView("treino/historicoTreinos", "treinos",treinoService.treinosPessoa(id));
	}

	@RequestMapping(value = "treino/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView treinoPessoa(@PathVariable("id") Long id) {
		return new ModelAndView("treino/exerciciosTreino", "treino",treinoService.getById(id));
	}
	
	@RequestMapping(value = "treinos/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView treinosPessoa(@PathVariable("id") Long id) {
		return new ModelAndView("treino/informacoes", "treinos",treinoService.treinosPessoa(id));
	}
	
	/*@RequestMapping(value = "informacoes/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView informacoesPessoais(@PathVariable("id") Long id) {
		return new ModelAndView("pessoa/informacoes", "treinos",treinoService.treinoPessoa(id));
	}*/
	
	// API Rest
	@RequestMapping(value = "api/treinos", method = RequestMethod.GET)
	public List<Treino> treinosApi() {
		return treinoService.treinos();
	}
	
	@RequestMapping(value = "api/treino/{id}", method = RequestMethod.GET)
	public Treino treinoApi(@PathVariable("id") Long id) {
		return treinoService.getById(id);
	}
	
	
}
