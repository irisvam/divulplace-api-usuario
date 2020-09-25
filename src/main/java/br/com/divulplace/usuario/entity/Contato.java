package br.com.divulplace.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_contato")
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cont_id_contato")
	private Long idContato;

	@Column(name = "cont_tl_fixo", length = 10)
	private String telFixo;

	@Column(name = "cont_tl_celular_primeiro", length = 10)
	private String celPrimeiro;

	@Column(name = "cont_tl_celular_segundo", length = 10)
	private String celSegundo;

	@NotNull
	@Column(name = "cont_ic_whatsapp_primeiro", columnDefinition = "boolean default false")
	private boolean icCelPriWhatsapp;

	@NotNull
	@Column(name = "cont_ic_whatsapp_segundo", columnDefinition = "boolean default false")
	private boolean icCelSegWhatsapp;

	@Column(name = "cont_nm_email_skype", length = 200)
	private String desSkype;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cont_id_afiliado", referencedColumnName = "afld_id_afiliado")
	private Afiliado afiliado;

	public Long getIdContato() {

		return idContato;
	}

	public void setIdContato(final Long idContato) {

		this.idContato = idContato;
	}

	public String getTelFixo() {

		return telFixo;
	}

	public void setTelFixo(final String telFixo) {

		this.telFixo = telFixo;
	}

	public String getCelPrimeiro() {

		return celPrimeiro;
	}

	public void setCelPrimeiro(final String celPrimeiro) {

		this.celPrimeiro = celPrimeiro;
	}

	public String getCelSegundo() {

		return celSegundo;
	}

	public void setCelSegundo(final String celSegundo) {

		this.celSegundo = celSegundo;
	}

	public boolean isIcCelPriWhatsapp() {

		return icCelPriWhatsapp;
	}

	public void setIcCelPriWhatsapp(final boolean icCelPriWhatsapp) {

		this.icCelPriWhatsapp = icCelPriWhatsapp;
	}

	public boolean isIcCelSegWhatsapp() {

		return icCelSegWhatsapp;
	}

	public void setIcCelSegWhatsapp(final boolean icCelSegWhatsapp) {

		this.icCelSegWhatsapp = icCelSegWhatsapp;
	}

	public String getDesSkype() {

		return desSkype;
	}

	public void setDesSkype(final String desSkype) {

		this.desSkype = desSkype;
	}

	public Afiliado getAfiliado() {

		return afiliado;
	}

	public void setAfiliado(final Afiliado afiliado) {

		this.afiliado = afiliado;
	}

}
