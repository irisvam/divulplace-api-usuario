package br.com.divulplace.usuario.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {

	@NotBlank
	@Size(min = 6, max = 200)
	private String username;

	@NotBlank
	@Size(min = 8, max = 20)
	private String password;

	public String getUsername() {

		return username;
	}

	public void setUsername(final String username) {

		this.username = username;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(final String password) {

		this.password = password;
	}

}
