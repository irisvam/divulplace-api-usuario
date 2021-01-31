package br.com.divulplace.usuario.ws.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.entity.Contato;
import br.com.divulplace.usuario.entity.RedeSocial;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserContato;
import br.com.divulplace.usuario.model.UserRedeSocial;
import br.com.divulplace.usuario.ws.repositories.ContatoRepository;
import br.com.divulplace.usuario.ws.repositories.RedeSocialRepository;

/**
 * Classe {@code Service} para formatação de dados dos contatos do Afiliado.
 */
@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repContato;

	@Autowired
	private RedeSocialRepository repRedeSocial;

	/**
	 * Método para encontrar os Contatos do Afiliado.
	 *
	 * @param idAfiliado {@link Long} com o {@code ID} do Afiliado
	 * @return {@link UserContato} com as informações dos contatos do Afiliado
	 */
	public UserContato encontrarContatoAfiliado(final Long idAfiliado) {

		UserContato userContato = null;

		final Contato contato = this.repContato.findByAfiliadoIdAfiliado(idAfiliado).orElse(null);

		if (null != contato) {

			userContato = new UserContato();

			userContato.setId(contato.getIdContato());
			userContato.setTelefone(contato.getTelFixo());
			userContato.setCelular01(contato.getCelPrimeiro());
			userContato.setCelular01WS(contato.isIcCelPriWhatsapp());
			userContato.setCelular02(contato.getCelSegundo());
			userContato.setCelular02WS(contato.isIcCelSegWhatsapp());
			userContato.setSkype(contato.getDesSkype());
		}

		return userContato;
	}

	/**
	 * Método para atualizar o contato do Afiliado.
	 *
	 * @param userContato {@link UserContato} com as informações do contato do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se atualizado com sucesso
	 */
	public boolean atualizarContato(final UserContato userContato) {

		boolean icAtualizado = false;

		final Contato contato = this.repContato.findById(userContato.getId()).orElse(null);

		if (null != contato) {

			contato.setTelFixo(userContato.getTelefone());
			contato.setCelPrimeiro(userContato.getCelular01());
			contato.setIcCelPriWhatsapp(userContato.isCelular01WS());
			contato.setCelSegundo(userContato.getCelular02());
			contato.setIcCelSegWhatsapp(userContato.isCelular02WS());
			contato.setDesSkype(userContato.getSkype());

			this.repContato.save(contato);
			icAtualizado = true;
		}

		return icAtualizado;
	}

	/**
	 * Método para atualização da lista de Redes Sociais do Afiliado.
	 *
	 * @param afiliado {@link Afiliado} com informações do Afiliado
	 * @param redesSociais {@code Lis<}{@link UserRedeSocial>}{@code >} com as lista de redes sociais
	 * @return {@code boolean} com {@code TRUE|FALSE} se atualizado com sucesso
	 */
	public boolean atualizarRedesSociais(final Afiliado afiliado, final List<UserRedeSocial> redesSociais) {

		boolean icAtualizado = false;

		final Set<RedeSocial> redesAtual = new HashSet<RedeSocial>();
		final Set<RedeSocial> redesRemover = new HashSet<RedeSocial>();

		for (final UserRedeSocial userRedeSocial : redesSociais) {

			if (null != userRedeSocial.getId() && 0L != userRedeSocial.getId()) {

				final RedeSocial redeSocial = this.repRedeSocial.findById(userRedeSocial.getId()).orElse(null);

				if (userRedeSocial.isSituacao()) {

					this.adequarRedeSocial(redeSocial, userRedeSocial);

					redesAtual.add(redeSocial);
				} else {

					redesRemover.add(redeSocial);
				}
			} else if (userRedeSocial.isSituacao()) {

				final RedeSocial redeSocial = new RedeSocial();
				redeSocial.setAfiliado(afiliado);

				this.adequarRedeSocial(redeSocial, userRedeSocial);
				redesAtual.add(redeSocial);
			}
		}

		if (!redesAtual.isEmpty()) {

			this.repRedeSocial.saveAll(redesAtual);
			icAtualizado = true;
		}

		if (!redesRemover.isEmpty()) {

			this.repRedeSocial.deleteAll(redesRemover);
			icAtualizado = true;
		}

		return icAtualizado;
	}

	/**
	 * Método de adequação de dados da Rede Social
	 *
	 * @param redeSocial {@link RedeSocial} com o item de cadastro a ser adequado
	 * @param userRedeSocial {@code UserRedeSocial} com o item a adequar a entidade.
	 */
	private void adequarRedeSocial(final RedeSocial redeSocial, final UserRedeSocial userRedeSocial) {

		redeSocial.setTpoSocial(userRedeSocial.getIcone());
		redeSocial.setUrlLink(userRedeSocial.getUrl());
	}

	/**
	 * Método para cadastro de novos conattos para o Afiliado.
	 *
	 * @param afiliado {@link Afiliado} com informações do Afiliado
	 * @param userContato {@link UserContato} com as informações dos contatos do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se cadastrado com sucesso
	 */
	public RetornoCadastro cadastrarContato(final Afiliado afiliado, final UserContato userContato) {

		final Contato contato = new Contato();
		contato.setTelFixo(userContato.getTelefone());
		contato.setCelPrimeiro(userContato.getCelular01());
		contato.setIcCelPriWhatsapp(userContato.isCelular01WS());
		contato.setCelSegundo(userContato.getCelular02());
		contato.setIcCelPriWhatsapp(userContato.isCelular02WS());
		contato.setDesSkype(userContato.getSkype());

		contato.setAfiliado(afiliado);

		final Contato cadastrado = this.repContato.save(contato);

		final RetornoCadastro retorno = new RetornoCadastro();
		retorno.setId(cadastrado.getIdContato());

		return retorno;
	}

}
