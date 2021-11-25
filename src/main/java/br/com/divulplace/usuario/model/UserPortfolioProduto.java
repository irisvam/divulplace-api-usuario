package br.com.divulplace.usuario.model;

public class UserPortfolioProduto {

	private Long id;
	private String tpoModelo;
	private String nomeProduto;
	private String descricao;
	private String urlProduto;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getTpoModelo() {

		return tpoModelo;
	}

	public void setTpoModelo(final String tpoModelo) {

		this.tpoModelo = tpoModelo;
	}

	public String getNomeProduto() {

		return nomeProduto;
	}

	public void setNomeProduto(final String nomeProduto) {

		this.nomeProduto = nomeProduto;
	}

	public String getDescricao() {

		return descricao;
	}

	public void setDescricao(final String descricao) {

		this.descricao = descricao;
	}

	public String getUrlProduto() {

		return urlProduto;
	}

	public void setUrlProduto(final String urlProduto) {

		this.urlProduto = urlProduto;
	}

}
