package br.com.divulplace.usuario.model;

import java.util.List;

public class UserPortfolioEmpresa {

	private Long id;
	private String nomeEmpresa;
	private String descricao;
	private String cnpj;
	private String urlEmpresa;
	private String urlVideo;
	private List<UserRamoAtividade> ramoAtividades;
	private EmpresaContato contato;
	private EmpresaEndereco endereco;

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

	public String getCnpj() {

		return cnpj;
	}

	public void setCnpj(final String cnpj) {

		this.cnpj = cnpj;
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

	public EmpresaContato getContato() {

		return contato;
	}

	public void setContato(final EmpresaContato contato) {

		this.contato = contato;
	}

	public EmpresaEndereco getEndereco() {

		return endereco;
	}

	public void setEndereco(final EmpresaEndereco endereco) {

		this.endereco = endereco;
	}

}
