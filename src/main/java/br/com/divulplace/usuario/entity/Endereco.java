package br.com.divulplace.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ende_id_endereco")
	private Long idEndereco;

	@Column(name = "ende_nm_endereco", length = 200)
	private String desEndereco;

	@Column(name = "ende_nu_numero")
	private Short numEndereco;

	@Column(name = "ende_nm_complemento", length = 100)
	private String desComplemento;

	@Column(name = "ende_cd_cep", length = 9)
	private String codCEP;

	@Column(name = "ende_nm_bairro", length = 150)
	private String desBairro;

	@Column(name = "ende_cd_pais", length = 3)
	private String codPais;

	@Column(name = "ende_uf_estado", length = 2)
	private String ufEstado;

	@Column(name = "ende_cd_cidade")
	private Integer codCidade;

	@Column(name = "ende_nm_cidade", length = 100)
	private String desCidade;

	public Long getIdEndereco() {

		return idEndereco;
	}

	public void setIdEndereco(final Long idEndereco) {

		this.idEndereco = idEndereco;
	}

	public String getDesEndereco() {

		return desEndereco;
	}

	public void setDesEndereco(final String desEndereco) {

		this.desEndereco = desEndereco;
	}

	public Short getNumEndereco() {

		return numEndereco;
	}

	public void setNumEndereco(final Short numEndereco) {

		this.numEndereco = numEndereco;
	}

	public String getDesComplemento() {

		return desComplemento;
	}

	public void setDesComplemento(final String desComplemento) {

		this.desComplemento = desComplemento;
	}

	public String getCodCEP() {

		return codCEP;
	}

	public void setCodCEP(final String codCEP) {

		this.codCEP = codCEP;
	}

	public String getDesBairro() {

		return desBairro;
	}

	public void setDesBairro(final String desBairro) {

		this.desBairro = desBairro;
	}

	public String getCodPais() {

		return codPais;
	}

	public void setCodPais(final String codPais) {

		this.codPais = codPais;
	}

	public String getUfEstado() {

		return ufEstado;
	}

	public void setUfEstado(final String ufEstado) {

		this.ufEstado = ufEstado;
	}

	public Integer getCodCidade() {

		return codCidade;
	}

	public void setCodCidade(final Integer codCidade) {

		this.codCidade = codCidade;
	}

	public String getDesCidade() {
	
		return desCidade;
	}

	public void setDesCidade(final String desCidade) {
	
		this.desCidade = desCidade;
	}

}
