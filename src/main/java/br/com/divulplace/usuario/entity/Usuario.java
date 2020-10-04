package br.com.divulplace.usuario.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import br.com.divulplace.usuario.util.enums.TipoSituacao;

@Entity
@Table(name = "tb_usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "user_nm_login" }),
		@UniqueConstraint(columnNames = { "user_nm_email" }) })
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id_usuario")
	private Long id;

	@NotEmpty
	@NaturalId
	@Size(min = 6, max = 200)
	@Column(name = "user_nm_login", length = 20)
	private String login;

	@NotEmpty
	@Size(min = 6, max = 100)
	@Column(name = "user_cr_password", length = 100)
	private String senha;

	@NotEmpty
	@Size(max = 150)
	@Column(name = "user_nm_nome", length = 150)
	private String nome;

	@NotEmpty
	@Size(min = 6, max = 200)
	@Email
	@Column(name = "user_nm_email", length = 200)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_tp_situacao", length = 15)
	private TipoSituacao tpoSituacao;

	@ManyToMany
	@JoinTable(name = "tb_usuario_tb_perfil", joinColumns = @JoinColumn(name = "usro_id_usuario", referencedColumnName = "user_id_usuario"), inverseJoinColumns = @JoinColumn(name = "usro_id_perfil", referencedColumnName = "role_id_perfil"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Role> roles = new HashSet<Role>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_dt_cadastro")
	private Date dtaCadastro;

	public Usuario() {

		super();
	}

	public Usuario(final String login, final String senha, final String nome, final String email,
			final TipoSituacao tpoSituacao) {

		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
		this.tpoSituacao = tpoSituacao;
	}

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public String getLogin() {

		return login;
	}

	public void setLogin(final String login) {

		this.login = login;
	}

	public String getSenha() {

		return senha;
	}

	public void setSenha(final String senha) {

		this.senha = senha;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(final String nome) {

		this.nome = nome;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public TipoSituacao getTpoSituacao() {

		return tpoSituacao;
	}

	public void setTpoSituacao(final TipoSituacao tpoSituacao) {

		this.tpoSituacao = tpoSituacao;
	}

	public Set<Role> getRoles() {

		return roles;
	}

	public void setRoles(final Set<Role> roles) {

		this.roles = roles;
	}

	public Date getDtaCadastro() {

		return dtaCadastro;
	}

	public void setDtaCadastro(final Date dtaCadastro) {

		this.dtaCadastro = dtaCadastro;
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
