package br.com.divulplace.usuario.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.RedeSocial;
import br.com.divulplace.usuario.model.UserRedeSocial;
import br.com.divulplace.usuario.ws.repositories.RedeSocialRepository;

/**
 * Classe {@code Service} para formatação de dados das Redes Sociais do Afiliado.
 */
@Service
public class RedeSocialService {

	@Autowired
	private RedeSocialRepository repRedeSocial;

	/**
	 * Método para inicializar Lista de Redes Sociais do Afiliado.
	 *
	 * @param idAfiliado {@link Long} com o {@code ID} do Afiliado
	 * @return {@code List<}{@link UserRedeSocial}{@code >}
	 */
	public List<UserRedeSocial> encontrarListaRedesSociais(final Long idAfiliado) {

		final List<UserRedeSocial> redesSociais = new ArrayList<UserRedeSocial>();

		final Set<RedeSocial> redeSociais = this.repRedeSocial.findAllByAfiliadoIdAfiliado(idAfiliado);

		if (null != redeSociais && !redeSociais.isEmpty()) {

			redeSociais.forEach(social -> {
				redesSociais.add(this.acrescentarRedeSocial(social));
			});
		}

		return redesSociais;
	}

	/**
	 * Método para formatação de informações de retorno de Rede Social.
	 *
	 * @param social {@link RedeSocial} com informação da base de dados
	 * @return {@link UserRedeSocial} com informação formatada
	 */
	private UserRedeSocial acrescentarRedeSocial(final RedeSocial social) {

		final UserRedeSocial redeSocial = new UserRedeSocial();
		redeSocial.setId(social.getIdSocial());
		redeSocial.setIcone(social.getTpoSocial());
		redeSocial.setUrl(social.getUrlLink());
		redeSocial.setSituacao(true);

		return redeSocial;
	}

}
