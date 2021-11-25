package br.com.divulplace.usuario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.divulplace.usuario.util.enums.TipoModeloProduto;

@Entity
@Table(name = "tb_portfolio_produto")
public class PortfolioProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ppro_id_produto")
	private Long idProduto;

	@Enumerated(EnumType.STRING)
	@Column(name = "ppro_tp_modelo", length = 15)
	private TipoModeloProduto tpoModelo;

	@Column(name = "ppro_nm_produto", length = 150)
	private String nomProduto;

	@Column(name = "ppro_nm_descricao", length = 500)
	private String desProduto;

	@Column(name = "ppro_ur_divulgacao", length = 200)
	private String urlDivulgacao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ppro_id_afiliado", referencedColumnName = "afld_id_afiliado")
	private Afiliado afiliado;

	public Long getIdProduto() {

		return idProduto;
	}

	public void setIdProduto(final Long idProduto) {

		this.idProduto = idProduto;
	}

	public TipoModeloProduto getTpoModelo() {

		return tpoModelo;
	}

	public void setTpoModelo(final TipoModeloProduto tpoModelo) {

		this.tpoModelo = tpoModelo;
	}

	public String getNomProduto() {

		return nomProduto;
	}

	public void setNomProduto(final String nomProduto) {

		this.nomProduto = nomProduto;
	}

	public String getDesProduto() {

		return desProduto;
	}

	public void setDesProduto(final String desProduto) {

		this.desProduto = desProduto;
	}

	public String getUrlDivulgacao() {

		return urlDivulgacao;
	}

	public void setUrlDivulgacao(final String urlDivulgacao) {

		this.urlDivulgacao = urlDivulgacao;
	}

	public Afiliado getAfiliado() {

		return afiliado;
	}

	public void setAfiliado(final Afiliado afiliado) {

		this.afiliado = afiliado;
	}

}
