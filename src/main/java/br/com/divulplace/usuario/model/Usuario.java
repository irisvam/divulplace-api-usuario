package br.com.divulplace.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id_usuario")
	private Long id;

	@NotEmpty
	@Size(max = 20)
	@Column(name = "user_nm_login", length = 20)
	private String login;

	@NotEmpty
	@Size(max = 100)
	@Column(name = "user_cr_password", length = 100)
	private String senha;

	@NotEmpty
	@Size(max = 100)
	@Column(name = "user_nm_name", length = 100)
	private String nome;

	@NotEmpty
	@Size(max = 200)
	@Column(name = "user_nm_email", length = 200)
	private String email;

	@NotEmpty
	@Size(max = 15)
	@Column(name = "user_tp_situacao", length = 15)
	private String situacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
