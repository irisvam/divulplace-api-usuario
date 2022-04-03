package br.com.divulplace.usuario.model;

public class EmpresaEndereco {

	private Long id;
	private String pais;
	private String cep;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String estado;
	private Integer cidadeId;
	private String cidade;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getPais() {

		return pais;
	}

	public void setPais(final String pais) {

		this.pais = pais;
	}

	public String getCep() {

		return cep;
	}

	public void setCep(final String cep) {

		this.cep = cep;
	}

	public String getLogradouro() {

		return logradouro;
	}

	public void setLogradouro(String logradouro) {

		this.logradouro = logradouro;
	}

	public Integer getNumero() {

		return numero;
	}

	public void setNumero(final Integer numero) {

		this.numero = numero;
	}

	public String getComplemento() {

		return complemento;
	}

	public void setComplemento(final String complemento) {

		this.complemento = complemento;
	}

	public String getBairro() {

		return bairro;
	}

	public void setBairro(final String bairro) {

		this.bairro = bairro;
	}

	public String getEstado() {

		return estado;
	}

	public void setEstado(final String estado) {

		this.estado = estado;
	}

	public Integer getCidadeId() {

		return cidadeId;
	}

	public void setCidadeId(final Integer cidadeId) {

		this.cidadeId = cidadeId;
	}

	public String getCidade() {

		return cidade;
	}

	public void setCidade(final String cidade) {

		this.cidade = cidade;
	}

}
