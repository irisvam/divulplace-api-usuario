package br.com.divulplace.usuario.model;

public class UserRedeSocial {

	private Long id;
	private String url;
	private String icone;
	private boolean situacao;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(final String url) {

		this.url = url;
	}

	public String getIcone() {

		return icone;
	}

	public void setIcone(final String icone) {

		this.icone = icone;
	}

	public boolean isSituacao() {

		return situacao;
	}

	public void setSituacao(final boolean situacao) {

		this.situacao = situacao;
	}

}
