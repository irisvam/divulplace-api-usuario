package br.com.divulplace.usuario.model;

public class EmpresaContato {

	private Long id;
	private String telefone;
	private String celular01;
	private boolean celular01WS;
	private String celular02;
	private boolean celular02WS;
	private String email;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getTelefone() {

		return telefone;
	}

	public void setTelefone(final String telefone) {

		this.telefone = telefone;
	}

	public String getCelular01() {

		return celular01;
	}

	public void setCelular01(final String celular01) {

		this.celular01 = celular01;
	}

	public boolean isCelular01WS() {

		return celular01WS;
	}

	public void setCelular01WS(final boolean celular01ws) {

		celular01WS = celular01ws;
	}

	public String getCelular02() {

		return celular02;
	}

	public void setCelular02(final String celular02) {

		this.celular02 = celular02;
	}

	public boolean isCelular02WS() {

		return celular02WS;
	}

	public void setCelular02WS(final boolean celular02ws) {

		celular02WS = celular02ws;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

}
