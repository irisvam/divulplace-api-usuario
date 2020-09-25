package br.com.divulplace.usuario.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.divulplace.usuario.util.enums.TipoEstadoCivil;
import br.com.divulplace.usuario.util.enums.TipoSexo;

@Entity
@Table(name = "tb_afiliado")
public class Afiliado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "afld_id_afiliado")
	private Long idAfiliado;

	@Column(name = "afld_nm_tratamento", length = 200)
	private String desTratamento;

	@Column(name = "afld_nm_nome", length = 200)
	private String desNome;

	@Column(name = "afld_nm_apelido", length = 150)
	private String desApelido;

	@Column(name = "afld_nm_sobre", length = 500)
	private String desSobre;

	@Temporal(TemporalType.DATE)
	@Column(name = "afld_dt_nascimento")
	private Date dtaNascimento;

	@Column(name = "afld_nm_cpf", length = 500)
	private String docCpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "afld_tp_estado_civil", length = 10)
	private TipoEstadoCivil tpoEstadoCivil;

	@Enumerated(EnumType.STRING)
	@Column(name = "afld_tp_sexo", length = 15)
	private TipoSexo tpoSexo;

	@Column(name = "afld_ur_imagem", length = 500)
	private String urlImagem;

	@Column(name = "afld_ur_link", length = 500)
	private String urlLink;

	@Column(name = "afld_nm_email", length = 200)
	private String desEmail;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "afld_id_usuario", referencedColumnName = "user_id_usuario")
	private Usuario usuario;

	public Long getIdAfiliado() {

		return idAfiliado;
	}

	public void setIdAfiliado(final Long idAfiliado) {

		this.idAfiliado = idAfiliado;
	}

	public String getDesTratamento() {

		return desTratamento;
	}

	public void setDesTratamento(final String desTratamento) {

		this.desTratamento = desTratamento;
	}

	public String getDesNome() {

		return desNome;
	}

	public void setDesNome(final String desNome) {

		this.desNome = desNome;
	}

	public String getDesApelido() {

		return desApelido;
	}

	public void setDesApelido(final String desApelido) {

		this.desApelido = desApelido;
	}

	public String getDesSobre() {

		return desSobre;
	}

	public void setDesSobre(final String desSobre) {

		this.desSobre = desSobre;
	}

	public Date getDtaNascimento() {

		return dtaNascimento;
	}

	public void setDtaNascimento(final Date dtaNascimento) {

		this.dtaNascimento = dtaNascimento;
	}

	public String getDocCpf() {

		return docCpf;
	}

	public void setDocCpf(final String docCpf) {

		this.docCpf = docCpf;
	}

	public TipoEstadoCivil getTpoEstadoCivil() {

		return tpoEstadoCivil;
	}

	public void setTpoEstadoCivil(final TipoEstadoCivil tpoEstadoCivil) {

		this.tpoEstadoCivil = tpoEstadoCivil;
	}

	public TipoSexo getTpoSexo() {

		return tpoSexo;
	}

	public void setTpoSexo(final TipoSexo tpoSexo) {

		this.tpoSexo = tpoSexo;
	}

	public String getUrlImagem() {

		return urlImagem;
	}

	public void setUrlImagem(final String urlImagem) {

		this.urlImagem = urlImagem;
	}

	public String getUrlLink() {

		return urlLink;
	}

	public void setUrlLink(final String urlLink) {

		this.urlLink = urlLink;
	}

	public String getDesEmail() {

		return desEmail;
	}

	public void setDesEmail(final String desEmail) {

		this.desEmail = desEmail;
	}

	public Usuario getUsuario() {

		return usuario;
	}

	public void setUsuario(final Usuario usuario) {

		this.usuario = usuario;
	}

}
