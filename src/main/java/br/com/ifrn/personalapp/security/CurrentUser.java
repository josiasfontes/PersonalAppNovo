package br.com.ifrn.personalapp.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import br.com.ifrn.personalapp.models.Pessoa;

public class CurrentUser extends User{

	private static final long serialVersionUID = 1L;
	
	private Pessoa user;
	
	public CurrentUser(Pessoa user) {
		super(user.getEmail(), user.getSenha(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}
	
	public Pessoa getUser() {
		return user;
	}
	
	public Long getId() {
		return user.getIdPessoa();
	}
	

	
}