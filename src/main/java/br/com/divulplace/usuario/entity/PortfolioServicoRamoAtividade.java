package br.com.divulplace.usuario.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_servico_ramo_atividade")
public class PortfolioServicoRamoAtividade implements Serializable {

	private static final long serialVersionUID = 5332705955526484901L;

	@EmbeddedId
	private PortfolioServicoRamoAtividadeId id;

	public PortfolioServicoRamoAtividadeId getId() {

		return id;
	}

	public void setId(final PortfolioServicoRamoAtividadeId id) {

		this.id = id;
	}

}
