package br.com.ifrn.personalapp.controllers;

import java.util.Calendar;
import java.util.Date;
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

import br.com.ifrn.personalapp.models.Mensalidade;
import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.security.CurrentUser;
import br.com.ifrn.personalapp.service.MensalidadeService;
import br.com.ifrn.personalapp.service.PessoaService;

@RestController
public class MensalidadeController {

	Pessoa pessoa = new Pessoa();
	Pessoa p = new Pessoa();
	
	
	@Autowired MensalidadeService mensalidadeService;
	@Autowired PessoaService pessoaService;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "mensalidade/criar", method = RequestMethod.GET)
	public ModelAndView formCriar(@ModelAttribute Mensalidade mensalidade) {
		return new ModelAndView("mensalidade/form");
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "mensalidade/criar", method = RequestMethod.POST)
	public ModelAndView criarMensalidade(@ModelAttribute Mensalidade mensalidade) {
		if (mensalidade.getIdMensalidade() == null) {
			mensalidade.setPessoa(pessoa);
			mensalidadeService.salvarMensalidade(mensalidade);
		} else {
			mensalidadeService.atualizarMensalidade(mensalidade);
		}
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		p = pessoaService.pessoasPorId(currentUser.getId());

		return new ModelAndView("pessoa/listar", "pessoas",
				pessoaService.pessoasPorAcademiaAtivas(p.getAcademia().getIdAcademia()));
		
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "mensalidade/pagar/{id}", method=RequestMethod.GET)
	public ModelAndView pagar(@PathVariable("id") Long id) {
		Mensalidade m = mensalidadeService.getById(id);
		Date data = new Date();
		m.setPago(true);
		m.setUltimoPagamento(data);
		
		Calendar c = Calendar.getInstance();
	 	c.setTime(data);
	 	c.add(Calendar.DATE, +30);
	 	data = c.getTime();
		
		m.setDataVencimento(data);
		mensalidadeService.atualizarMensalidade(m);
		
		CurrentUser currentUser = (CurrentUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		p = pessoaService.pessoasPorId(currentUser.getId());
		return new ModelAndView("pessoa/listar", "pessoas",
				pessoaService.pessoasPorAcademiaAtivas(p.getAcademia().getIdAcademia()));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "mensalidade/editar/{id}", method=RequestMethod.GET)
	public ModelAndView formEditar(@PathVariable("id") Long id) {
		return new ModelAndView("mensalidade/form", "mensalidade", mensalidadeService.getById(id));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "mensalidade/listar", method = RequestMethod.GET) 
	public ModelAndView listar() {
		return new ModelAndView("mensalidade/listar", "mensalidades",mensalidadeService.mensalidadesNaoPagaPessoa(pessoa.getIdPessoa()));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "historicomensalidades/listar", method = RequestMethod.GET) 
	public ModelAndView historico() {
		return new ModelAndView("mensalidade/listarHistorico", "mensalidades",mensalidadeService.mensalidadesPagaPessoa(pessoa.getIdPessoa()));
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "mensalidades/pessoa/{id}", method = RequestMethod.GET)
	public ModelAndView mensalidadePessoa(@PathVariable("id") Long id) {
		pessoa.setIdPessoa(id);
		return new ModelAndView("mensalidade/listar", "mensalidades",mensalidadeService.mensalidadesNaoPagaPessoa(id));
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
