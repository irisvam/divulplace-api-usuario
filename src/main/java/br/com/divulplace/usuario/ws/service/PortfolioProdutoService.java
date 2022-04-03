package br.com.divulplace.usuario.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.entity.PortfolioProduto;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserPortfolioProduto;
import br.com.divulplace.usuario.util.enums.TipoModeloProduto;
import br.com.divulplace.usuario.ws.repositories.IPortfolioProdutoRepository;

/**
 * Classe {@code Service} para formatação de dados de Produtos do Portfólio.
 */
@Service
public class PortfolioProdutoService {

	@Autowired
	private IPortfolioProdutoRepository repProduto;

	/**
	 * Método para inicializar Lista de Produtos do Portfolio do Afiliado.
	 *
	 * @param idAfiliado {@link Long} com o {@code ID} do Afiliado
	 * @return {@code List<}{@link UserPortfolioProduto}{@code >}
	 */
	public List<UserPortfolioProduto> encontrarListaPortfolioProdutos(final Long idAfiliado) {

		final List<UserPortfolioProduto> lista = new ArrayList<UserPortfolioProduto>();

		final Set<PortfolioProduto> portProdutos = this.repProduto.findAllByAfiliadoIdAfiliado(idAfiliado);

		if (null != portProdutos && !portProdutos.isEmpty()) {

			portProdutos.forEach(servico -> {
				lista.add(this.acrescentarProduto(servico));
			});
		}

		return lista;
	}

	/**
	 * Método para cadastro do Produto de Portfólio do Afiliado.
	 *
	 * @param afiliado {@link Afiliado} com informações do Afiliado
	 * @param userProduto {@link UserPortfolioProduto} com as informações do Produto do Portfólio do Afiliado
	 * @return {@code RetornoCadastro} com o {@code ID} cadastrado com sucesso
	 */
	public RetornoCadastro cadastrarPortfolioProduto(final Afiliado afiliado, @Valid final UserPortfolioProduto userProduto) {

		final PortfolioProduto portProduto = new PortfolioProduto();
		portProduto.setNomProduto(userProduto.getNomeProduto());
		portProduto.setDesProduto(userProduto.getDescricao());
		portProduto.setTpoModelo(TipoModeloProduto.valueOf(userProduto.getTpoModelo()));
		portProduto.setUrlDivulgacao(userProduto.getUrlProduto());
		portProduto.setAfiliado(afiliado);

		final PortfolioProduto cadastrado = this.repProduto.save(portProduto);

		final RetornoCadastro retorno = new RetornoCadastro();
		retorno.setId(cadastrado.getIdProduto());

		return retorno;
	}

	/**
	 * Método para encontrar dados do Produto desejado.
	 *
	 * @param idProduto {@link Long} com o {@code ID} do produto
	 * @return {@link UserPortfolioProduto} com as informações do Produto
	 */
	public UserPortfolioProduto encontrarProduto(final Long idProduto) {

		UserPortfolioProduto userProduto = null;

		final PortfolioProduto produto = this.repProduto.findById(idProduto).orElse(null);

		if (null != produto) {

			userProduto = acrescentarProduto(produto);
		}

		return userProduto;
	}

	/**
	 * Método para atualizar o Produto de Portfólio do Afiliado.
	 *
	 * @param idProduto {@code ID} do Produto a ser atualizado
	 * @param userProduto {@link UserPortfolioProduto} com as informações do Produto do Portfólio do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se atualizado com sucesso
	 */
	public boolean atualizarPortfolioProduto(final Long idProduto, @Valid final UserPortfolioProduto userProduto) {

		boolean icAtualizado = false;

		final PortfolioProduto produto = this.repProduto.findById(idProduto).orElse(null);

		if (null != produto) {

			produto.setNomProduto(userProduto.getNomeProduto());
			produto.setDesProduto(userProduto.getDescricao());
			produto.setTpoModelo(TipoModeloProduto.valueOf(userProduto.getTpoModelo()));
			produto.setUrlDivulgacao(userProduto.getUrlProduto());

			this.repProduto.save(produto);
			icAtualizado = true;
		}

		return icAtualizado;
	}

	/**
	 * Método para deletar Produto.
	 *
	 * @param idProduto {@code ID} do Produto
	 * @return {@code boolean} com {@code TRUE|FALSE} se deletado com sucesso
	 */
	public boolean deletarProduto(final Long idProduto) {

		boolean retorno = false;

		final PortfolioProduto produto = this.repProduto.findById(idProduto).orElse(null);

		if (null != produto) {

			this.repProduto.deleteById(idProduto);
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Método para preparar o Produto para ser enviado externamente.
	 *
	 * @param produto {@link PortfolioProduto} com as informações do produto cadastrado
	 * @return {@link UserPortfolioProduto} com os dados do Produto a ser informado
	 */
	private UserPortfolioProduto acrescentarProduto(final PortfolioProduto produto) {

		final UserPortfolioProduto portProduto = new UserPortfolioProduto();
		portProduto.setId(produto.getIdProduto());
		portProduto.setTpoModelo(produto.getTpoModelo().name());
		portProduto.setNomeProduto(produto.getNomProduto());
		portProduto.setDescricao(produto.getDesProduto());
		portProduto.setUrlProduto(produto.getUrlDivulgacao());

		return portProduto;
	}

}
