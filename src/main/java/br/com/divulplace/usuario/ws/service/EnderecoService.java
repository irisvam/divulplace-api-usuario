package br.com.divulplace.usuario.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.entity.Endereco;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserEndereco;
import br.com.divulplace.usuario.ws.repositories.EnderecoRepository;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;

/**
 * Classe {@code Service} para formatação de dados do Endereço do Afiliado.
 */
@Service
public class EnderecoService {

	@Autowired
	private PerfilRepository repPerfil;

	@Autowired
	private EnderecoRepository repEndereco;

	/**
	 * Método para encontrar o Endereço do Afiliado.
	 *
	 * @param idAfiliado {@link Long} com o {@code ID} do Afiliado
	 * @return {@link UserEndereco} com as informações do endereço do Afiliado
	 */
	public UserEndereco encontrarEnderecoAfiliado(final Long idAfiliado) {

		UserEndereco userEndereco = null;

		final Afiliado afiliado = this.repPerfil.findById(idAfiliado).orElse(null);

		final Endereco endereco = afiliado.getEndereco();

		if (null != endereco) {

			userEndereco = new UserEndereco();

			userEndereco.setId(endereco.getIdEndereco());
			userEndereco.setPais(endereco.getCodPais());
			userEndereco.setCep(endereco.getCodCEP());
			userEndereco.setLogradouro(endereco.getDesEndereco());
			userEndereco.setNumero(endereco.getNumEndereco().intValue());
			userEndereco.setComplemento(endereco.getDesComplemento());
			userEndereco.setBairro(endereco.getDesBairro());
			userEndereco.setEstado(endereco.getUfEstado());
			userEndereco.setCidadeId(endereco.getCodCidade());
			userEndereco.setCidade(endereco.getDesCidade());
		}

		return userEndereco;
	}

	/**
	 * Método para atualizar o endereço do Afiliado.
	 *
	 * @param userEndereco {@link UserEndereco} com as informações do endereço do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se atualizado com sucesso
	 */
	public boolean atualizarEndereco(final UserEndereco userEndereco) {

		boolean icAtualizado = false;

		final Endereco endereco = this.repEndereco.findById(userEndereco.getId()).orElse(null);

		if (null != endereco) {

			endereco.setCodPais(userEndereco.getPais());
			endereco.setCodCEP(userEndereco.getCep());
			endereco.setDesEndereco(userEndereco.getLogradouro());
			endereco.setNumEndereco(null == userEndereco.getNumero() ? 0 : userEndereco.getNumero().shortValue());
			endereco.setDesComplemento(userEndereco.getComplemento());
			endereco.setDesBairro(userEndereco.getBairro());
			endereco.setUfEstado(userEndereco.getEstado());
			endereco.setCodCidade(null == userEndereco.getCidadeId() ? 0 : userEndereco.getCidadeId());
			endereco.setDesCidade(userEndereco.getCidade());

			this.repEndereco.save(endereco);
			icAtualizado = true;
		}

		return icAtualizado;
	}

	/**
	 * Método para cadastro do endereço do Afiliado.
	 *
	 * @param idAfiliado {@link Long} com o {@code ID} do Afiliado
	 * @param userEndereco {@link UserEndereco} com as informações do endereço do Afiliado
	 * @return {@code RetornoCadastro} com o {@code ID} cadastrado com sucesso
	 */
	public RetornoCadastro cadastrarEndereco(final Long idAfiliado, final UserEndereco userEndereco) {

		RetornoCadastro retorno = null;

		final Endereco endereco = new Endereco();

		endereco.setCodPais(userEndereco.getPais());
		endereco.setCodCEP(userEndereco.getCep());
		endereco.setDesEndereco(userEndereco.getLogradouro());
		endereco.setNumEndereco(null == userEndereco.getNumero() ? 0 : userEndereco.getNumero().shortValue());
		endereco.setDesComplemento(userEndereco.getComplemento());
		endereco.setDesBairro(userEndereco.getBairro());
		endereco.setUfEstado(userEndereco.getEstado());
		endereco.setCodCidade(null == userEndereco.getCidadeId() ? 0 : userEndereco.getCidadeId());
		endereco.setDesCidade(userEndereco.getCidade());

		final Afiliado afiliado = this.repPerfil.findById(idAfiliado).orElse(null);
		
		if (null != afiliado) {

			final Endereco cadastrado = this.repEndereco.save(endereco);

			afiliado.setEndereco(endereco);
			this.repPerfil.save(afiliado);

			retorno = new RetornoCadastro();
			retorno.setId(cadastrado.getIdEndereco());
		}

		return retorno;
	}

}
