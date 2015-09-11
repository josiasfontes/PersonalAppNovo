package br.com.ifrn.personalapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.ifrn.personalapp.models.Mensalidade;
import br.com.ifrn.personalapp.service.MensalidadeService;
import br.com.ifrn.personalapp.service.PessoaService;

@RestController
public class MensalidadeController {

	@Autowired MensalidadeService mensalidadeService;
	@Autowired PessoaService pessoaService;

	@RequestMapping(value = "mensalidade/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Mensalidade mensalidade) {
		return new ModelAndView("mensalidade/form");
	}

	@RequestMapping(value = "mensalidade/criar", method = RequestMethod.POST)
	public ModelAndView criarMensalidade(@ModelAttribute Mensalidade mensalidade) {
		if (mensalidade.getIdMensalidade() == null) {
			mensalidadeService.salvarMensalidade(mensalidade);
		} else {
			mensalidadeService.atualizarMensalidade(mensalidade);
		}
		return new ModelAndView("mensalidade/listar", "mensalidades", mensalidadeService.mensalidades());
	}
	
	@RequestMapping(value = "mensalidade/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("mensalidade/form", "mensalidade", mensalidadeService.getById(id));
	}
	
	@RequestMapping(value = "mensalidade/listar", method = RequestMethod.GET) 
	public ModelAndView listar() {
		return new ModelAndView("mensalidade/listar", "mensalidades", mensalidadeService.mensalidades());
	}
	
	
	@RequestMapping(value = "mensalidades/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView mensalidadePessoa(@PathVariable("id") Long id) {
		return new ModelAndView("mensalidade/listar", "mensalidades",mensalidadeService.mensalidadePessoa(id));
	}

	
	// API Rest
	@RequestMapping(value = "api/mensalidades", method = RequestMethod.GET)
	public List<Mensalidade> mensalidadesApi() {
		return mensalidadeService.mensalidades();
	}
	
	@RequestMapping(value = "api/mensalidade/{id}", method = RequestMethod.GET)
	public Mensalidade mensalidadeApi(@PathVariable("id") Long id) {
		return mensalidadeService.getById(id);
	}
	
}
