package br.com.divulplace.usuario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PortfolioEmpresaRamoAtividadeId implements Serializable {

	private static final long serialVersionUID = -7468375908374537281L;

	@Column(name = "eatv_id_empresa")
	private Long idEmpresa;

	@Column(name = "eatv_id_ramo_atividade")
	private Long idRamo;

	public PortfolioEmpresaRamoAtividadeId(final Long idEmpresa, final Long idRamo) {

		super();
		this.idEmpresa = idEmpresa;
		this.idRamo = idRamo;
	}

	public Long getIdEmpresa() {

		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {

		this.idEmpresa = idEmpresa;
	}

	public Long getIdRamo() {

		return idRamo;
	}

	public void setIdRamo(final Long idRamo) {

		this.idRamo = idRamo;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRamo == null) ? 0 : idRamo.hashCode());
		result = prime * result + ((idEmpresa == null) ? 0 : idEmpresa.hashCode());

		return result;
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
		final PortfolioEmpresaRamoAtividadeId other = (PortfolioEmpresaRamoAtividadeId) obj;
		if (idRamo == null) {
			if (other.idRamo != null) {
				return false;
			}
		} else if (!idRamo.equals(other.idRamo)) {
			return false;
		}
		if (idEmpresa == null) {
			if (other.idEmpresa != null) {
				return false;
			}
		} else if (!idEmpresa.equals(other.idEmpresa)) {
			return false;
		}

		return true;
	}

}
