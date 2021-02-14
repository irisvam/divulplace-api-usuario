package br.com.divulplace.usuario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PortfolioServicoRamoAtividadeId implements Serializable {

	private static final long serialVersionUID = -7468375908374537281L;

	@Column(name = "satv_id_servico")
	private Long idServico;

	@Column(name = "satv_id_ramo_atividade")
	private Long idRamo;

	public PortfolioServicoRamoAtividadeId(final Long idServico, final Long idRamo) {

		super();
		this.idServico = idServico;
		this.idRamo = idRamo;
	}

	public Long getIdServico() {

		return idServico;
	}

	public void setIdServico(final Long idServico) {

		this.idServico = idServico;
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
		result = prime * result + ((idServico == null) ? 0 : idServico.hashCode());

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
		final PortfolioServicoRamoAtividadeId other = (PortfolioServicoRamoAtividadeId) obj;
		if (idRamo == null) {
			if (other.idRamo != null) {
				return false;
			}
		} else if (!idRamo.equals(other.idRamo)) {
			return false;
		}
		if (idServico == null) {
			if (other.idServico != null) {
				return false;
			}
		} else if (!idServico.equals(other.idServico)) {
			return false;
		}

		return true;
	}

}
