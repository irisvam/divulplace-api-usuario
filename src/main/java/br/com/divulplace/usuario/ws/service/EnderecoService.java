package br.com.divulplace.usuario.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.entity.Endereco;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserEndereco;
import br.com.divulplace.usuario.ws.repositories.EnderecoRepository;

/**
 * Classe {@code Service} para formatação de dados do Endereço do Afiliado.
 */
@Service
public class EnderecoService {

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

		final Endereco endereco = this.repEndereco.findByAfiliadoIdAfiliado(idAfiliado).orElse(null);

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
	 * Método para cadastro do endereço do Afliado.
	 *
	 * @param afiliado {@link Afiliado} com informações do Afiliado
	 * @param userEndereco {@link UserEndereco} com as informações do endereço do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se cadastrado com sucesso
	 */
	public RetornoCadastro cadastrarEndereco(final Afiliado afiliado, final UserEndereco userEndereco) {

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
		endereco.setAfiliado(afiliado);

		final Endereco cadastrado = this.repEndereco.save(endereco);

		final RetornoCadastro retorno = new RetornoCadastro();
		retorno.setId(cadastrado.getIdEndereco());

		return retorno;
	}

}
