package br.com.divulplace.usuario.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_empresa_ramo_atividade")
public class PortfolioEmpresaRamoAtividade implements Serializable {

	private static final long serialVersionUID = 5332705955526484901L;

	@EmbeddedId
	private PortfolioEmpresaRamoAtividadeId id;

	public PortfolioEmpresaRamoAtividadeId getId() {

		return id;
	}

	public void setId(final PortfolioEmpresaRamoAtividadeId id) {

		this.id = id;
	}

}
