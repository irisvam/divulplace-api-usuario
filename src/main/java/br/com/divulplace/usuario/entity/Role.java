package br.com.divulplace.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import br.com.divulplace.usuario.util.enums.RoleName;

@Entity
@Table(name = "tb_perfil")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id_perfil")
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "role_tp_perfil", length = 15)
	private RoleName nome;

	public Long getId() {

		return id;
	}

	public void setId(final Long id) {

		this.id = id;
	}

	public RoleName getNome() {

		return nome;
	}

	public void setNome(final RoleName nome) {

		this.nome = nome;
	}

}
