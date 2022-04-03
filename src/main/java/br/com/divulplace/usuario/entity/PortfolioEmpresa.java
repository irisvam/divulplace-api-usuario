package br.com.divulplace.usuario.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tb_portfolio_empresa")
public class PortfolioEmpresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pemp_id_empresa")
	private Long idEmpresa;

	@Column(name = "pemp_nm_empresa", length = 150)
	private String nomEmpresa;

	@Column(name = "pemp_nm_descricao", length = 500)
	private String desEmpresa;

	@Column(name = "pemp_dc_cnpj", length = 14)
	private String docCnpj;

	@Column(name = "pemp_ur_divulgacao", length = 200)
	private String urlDivulgacao;

	@Column(name = "pemp_ur_video", length = 200)
	private String urlVideo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pemp_id_afiliado", referencedColumnName = "afld_id_afiliado")
	private Afiliado afiliado;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pemp_id_endereco", referencedColumnName = "ende_id_endereco")
	private Endereco endereco;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pemp_id_contato", referencedColumnName = "cont_id_contato")
	private Contato contato;

	@ManyToMany
	@JoinTable(name = "tb_empresa_ramo_atividade", joinColumns = @JoinColumn(name = "eatv_id_empresa", referencedColumnName = "pemp_id_empresa"), inverseJoinColumns = @JoinColumn(name = "eatv_id_ramo_atividade", referencedColumnName = "ratv_id_ramo_atividade"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<RamoAtividade> lstRamoAtividade;

	public Long getIdEmpresa() {

		return idEmpresa;
	}

	public void setIdEmpresa(final Long idEmpresa) {

		this.idEmpresa = idEmpresa;
	}

	public String getNomEmpresa() {

		return nomEmpresa;
	}

	public void setNomEmpresa(final String nomEmpresa) {

		this.nomEmpresa = nomEmpresa;
	}

	public String getDesEmpresa() {

		return desEmpresa;
	}

	public void setDesEmpresa(final String desEmpresa) {

		this.desEmpresa = desEmpresa;
	}

	public String getDocCnpj() {

		return docCnpj;
	}

	public void setDocCnpj(final String docCnpj) {

		this.docCnpj = docCnpj;
	}

	public String getUrlDivulgacao() {

		return urlDivulgacao;
	}

	public void setUrlDivulgacao(final String urlDivulgacao) {

		this.urlDivulgacao = urlDivulgacao;
	}

	public String getUrlVideo() {

		return urlVideo;
	}

	public void setUrlVideo(final String urlVideo) {

		this.urlVideo = urlVideo;
	}

	public Afiliado getAfiliado() {

		return afiliado;
	}

	public void setAfiliado(final Afiliado afiliado) {

		this.afiliado = afiliado;
	}

	public Endereco getEndereco() {

		return endereco;
	}

	public void setEndereco(final Endereco endereco) {

		this.endereco = endereco;
	}

	public Contato getContato() {

		return contato;
	}

	public void setContato(final Contato contato) {

		this.contato = contato;
	}

	public Set<RamoAtividade> getLstRamoAtividade() {

		return lstRamoAtividade;
	}

	public void setLstRamoAtividade(final Set<RamoAtividade> lstRamoAtividade) {

		this.lstRamoAtividade = lstRamoAtividade;
	}

}
