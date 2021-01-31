package br.com.divulplace.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_ramo_atividade")
public class RamoAtividade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ratv_id_ramo_atividade")
	private Long idRamo;

	@Column(name = "ratv_nm_ramo_atividade", length = 50)
	private String nomRamo;

	public Long getIdRamo() {

		return idRamo;
	}

	public void setIdRamo(final Long idRamo) {

		this.idRamo = idRamo;
	}

	public String getNomRamo() {

		return nomRamo;
	}

	public void setNomRamo(final String nomRamo) {

		this.nomRamo = nomRamo;
	}

}
