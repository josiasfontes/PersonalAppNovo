package br.com.ifrn.personalapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Exercicio;
import br.com.ifrn.personalapp.service.ExercicioService;

@RestController
public class ExercicioController {

	@Autowired ExercicioService exercicioService;

	@RequestMapping(value = "exercicio/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Exercicio exercicio) {
		return new ModelAndView("exercicio/form");
	}

	@RequestMapping(value = "exercicio/criar", method = RequestMethod.POST)
	public ModelAndView criarExercicio(@ModelAttribute Exercicio exercicio) {
		if (exercicio.getIdExercicio() == null) {																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																								
			exercicioService.salvarExercicio(exercicio);
		}else{
			exercicioService.atualizarExercicio(exercicio);
		}
		return new ModelAndView("exercicio/listar","exercicios", exercicioService.exercicios());
	}
	
	//editar
	@RequestMapping(value = "exercicio/atualizar/{id}")
	public ModelAndView updateExercicio(@PathVariable Exercicio exercicio, @PathVariable Long id) {
		exercicio.setIdExercicio(id);
		exercicioService.atualizarExercicio(exercicio);
		return new ModelAndView("exercicio/listar","exercicios", exercicioService.exercicios());
	}
	
	//deletar
	@RequestMapping(value = "exercicio/deletar/{id}")
	public ModelAndView deletarExercicio(@PathVariable Long id) {
		exercicioService.removerExercicio(id);
		return new ModelAndView("exercicio/listar","exercicios", exercicioService.exercicios());
	}
	
	@RequestMapping(value = "exercicio/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("exercicio/form", "exercicio", exercicioService.getById(id));
	}
	
	// API Rest
	@RequestMapping(value = "api/exercicios", method = RequestMethod.GET)
	public List<Exercicio> exerciciosApi() {
		return exercicioService.exercicios();
	}
	
	@RequestMapping(value = "api/exercicio/{id}", method = RequestMethod.GET)
	public Exercicio exercicioApi(HttpServletResponse response,@PathVariable("id") Long id) {
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		return exercicioService.getById(id);
	}
	
	@RequestMapping(value = "exercicio/listar", method = RequestMethod.GET) 
	public ModelAndView listar() {
		return new ModelAndView("exercicio/listar", "exercicios", exercicioService.exercicios());
	}
	
	
}
