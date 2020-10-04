package br.com.divulplace.usuario.model;

import java.util.Date;

public class UserPerfil {

	private Long id;
	private String codigo;
	private String tratamento;
	private String img;
	private String nome;
	private String apelido;
	private String cpf;
	private Date dataNascimento;
	private String estadoCivil;
	private String sexo;
	private String email;
	private String link;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getCodigo() {

		return codigo;
	}

	public void setCodigo(final String codigo) {

		this.codigo = codigo;
	}

	public String getTratamento() {

		return tratamento;
	}

	public void setTratamento(final String tratamento) {

		this.tratamento = tratamento;
	}

	public String getImg() {

		return img;
	}

	public void setImg(final String img) {

		this.img = img;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(final String nome) {

		this.nome = nome;
	}

	public String getApelido() {

		return apelido;
	}

	public void setApelido(final String apelido) {

		this.apelido = apelido;
	}

	public String getCpf() {

		return cpf;
	}

	public void setCpf(final String cpf) {

		this.cpf = cpf;
	}

	public Date getDataNascimento() {

		return dataNascimento;
	}

	public void setDataNascimento(final Date dataNascimento) {

		this.dataNascimento = dataNascimento;
	}

	public String getEstadoCivil() {

		return estadoCivil;
	}

	public void setEstadoCivil(final String estadoCivil) {

		this.estadoCivil = estadoCivil;
	}

	public String getSexo() {

		return sexo;
	}

	public void setSexo(final String sexo) {

		this.sexo = sexo;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public String getLink() {

		return link;
	}

	public void setLink(final String link) {

		this.link = link;
	}

}
