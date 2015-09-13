package br.com.ifrn.personalapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ifrn.personalapp.models.Pessoa;
import br.com.ifrn.personalapp.service.PessoaService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

	private final PessoaService service;

	@Autowired
	public CurrentUserDetailsService(PessoaService userService) {
		this.service = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Pessoa user = service.getByEmail(email);
		if (user != null) {
			return new CurrentUser(user);
		}
		throw new UsernameNotFoundException(String.format(
				"Não foi encontrado usuário com email=%s", email));

	}
	
}
