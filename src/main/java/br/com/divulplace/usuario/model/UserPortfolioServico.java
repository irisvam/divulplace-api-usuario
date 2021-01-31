package br.com.divulplace.usuario.model;

import java.util.List;

public class UserPortfolioServico {

	private Long id;
	private String nomeEmpresa;
	private String descricao;
	private String identificacao;
	private String urlEmpresa;
	private String urlVideo;
	private List<UserRamoAtividade> ramoAtividades;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getNomeEmpresa() {

		return nomeEmpresa;
	}

	public void setNomeEmpresa(final String nomeEmpresa) {

		this.nomeEmpresa = nomeEmpresa;
	}

	public String getDescricao() {

		return descricao;
	}

	public void setDescricao(final String descricao) {

		this.descricao = descricao;
	}

	public String getIdentificacao() {

		return identificacao;
	}

	public void setIdentificacao(final String identificacao) {

		this.identificacao = identificacao;
	}

	public String getUrlEmpresa() {

		return urlEmpresa;
	}

	public void setUrlEmpresa(final String urlEmpresa) {

		this.urlEmpresa = urlEmpresa;
	}

	public String getUrlVideo() {

		return urlVideo;
	}

	public void setUrlVideo(final String urlVideo) {

		this.urlVideo = urlVideo;
	}

	public List<UserRamoAtividade> getRamoAtividades() {

		return ramoAtividades;
	}

	public void setRamoAtividades(final List<UserRamoAtividade> ramoAtividades) {

		this.ramoAtividades = ramoAtividades;
	}

}
