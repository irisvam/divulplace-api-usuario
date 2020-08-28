package br.com.divulplace.usuario.model;

public class UserLogin {

	private Long id;

	private String name;

	private String email;

	private String token;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public String getToken() {

		return token;
	}

	public void setToken(final String token) {

		this.token = token;
	}

}
