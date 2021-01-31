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

@Entity
@Table(name = "tb_portfolio_servico")
public class PortfolioServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "psev_id_servico")
	private Long idServico;

	@Column(name = "psev_nm_empresa", length = 150)
	private String nomEmpresa;

	@Column(name = "psev_nm_descricao", length = 500)
	private String desEmpresa;

	@Column(name = "psev_id_funcional", length = 100)
	private String idnFuncional;

	@Column(name = "psev_ur_divulgacao", length = 200)
	private String urlDivulgacao;

	@Column(name = "psev_ur_video", length = 200)
	private String urlVideo;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "psev_id_afiliado", referencedColumnName = "afld_id_afiliado")
	private Afiliado afiliado;

	@ManyToMany
	@JoinTable(name = "tb_servico_ramo_atividade", 
		joinColumns = @JoinColumn(name = "satv_id_servico", referencedColumnName = "psev_id_servico"), 
		inverseJoinColumns = @JoinColumn(name = "satv_id_ramo_atividade", referencedColumnName = "ratv_id_ramo_atividade"))
	private Set<RamoAtividade> lstRamoAtividade;

	public Long getIdServico() {

		return idServico;
	}

	public void setIdServico(final Long idServico) {

		this.idServico = idServico;
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

	public String getIdnFuncional() {

		return idnFuncional;
	}

	public void setIdnFuncional(final String idnFuncional) {

		this.idnFuncional = idnFuncional;
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

	public Set<RamoAtividade> getLstRamoAtividade() {

		return lstRamoAtividade;
	}

	public void setLstRamoAtividade(final Set<RamoAtividade> lstRamoAtividade) {

		this.lstRamoAtividade = lstRamoAtividade;
	}

}
