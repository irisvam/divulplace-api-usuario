package br.com.divulplace.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_rede_social")
public class RedeSocial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rdso_id_social")
	private Long idSocial;

	@Column(name = "rdso_tp_tipo", length = 10)
	private String tpoSocial;

	@Column(name = "rdso_ur_link", length = 200)
	private String urlLink;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rdso_id_afiliado", referencedColumnName = "afld_id_afiliado")
	private Afiliado afiliado;

	public Long getIdSocial() {

		return idSocial;
	}

	public void setIdSocial(final Long idSocial) {

		this.idSocial = idSocial;
	}

	public String getTpoSocial() {

		return tpoSocial;
	}

	public void setTpoSocial(final String tpoSocial) {

		this.tpoSocial = tpoSocial;
	}

	public String getUrlLink() {

		return urlLink;
	}

	public void setUrlLink(final String urlLink) {

		this.urlLink = urlLink;
	}

	public Afiliado getAfiliado() {

		return afiliado;
	}

	public void setAfiliado(final Afiliado afiliado) {

		this.afiliado = afiliado;
	}

}
