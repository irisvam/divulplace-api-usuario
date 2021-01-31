package br.com.divulplace.usuario.model;

public class UserRamoAtividade {

	private Long id;
	private String nome;
	private boolean situacao;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(final String nome) {

		this.nome = nome;
	}

	public boolean isSituacao() {

		return situacao;
	}

	public void setSituacao(final boolean situacao) {

		this.situacao = situacao;
	}

}
