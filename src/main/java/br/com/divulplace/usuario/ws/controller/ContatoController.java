package br.com.divulplace.usuario.ws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.model.ResponseMessage;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserContato;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;
import br.com.divulplace.usuario.ws.service.ContatoService;
import br.com.divulplace.usuario.ws.service.RedeSocialService;

/**
 * Classe {@code REST} de controle para serviços de Contato.
 */
@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/contato")
public class ContatoController extends CommonController {

	@Autowired
	private ContatoService serContato;

	@Autowired
	private RedeSocialService serSocial;

	@Autowired
	private PerfilRepository repPerfil;

	@GetMapping("/{id}")
	public ResponseEntity<UserContato> recuperarContato(@PathVariable(value = "id") final Long idAfiliado) {

		final UserContato userContato = this.serContato.encontrarContatoAfiliado(idAfiliado);

		if (null == userContato) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.GET_NOT_FOUND.getDescricao(),
					new Object[] {"contatos"}, super.getLocale()));
		}

		userContato.setRedesSociais(this.serSocial.encontrarListaRedesSociais(idAfiliado));

		return new ResponseEntity<UserContato>(userContato, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarContato(@PathVariable(value = "id") final Long idUsuario, @Valid @RequestBody final UserContato userContato) {

		final Afiliado afiliado = this.repPerfil.findById(idUsuario).orElse(null);

		if (null == afiliado) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Afiliado", "não encontrado"}, super.getLocale()));
		}

		if (!this.serContato.atualizarContato(userContato)) {

			throw new ParameterNotValidException(
					super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(), new Object[] {"contatos"}, super.getLocale()));
		}

		if (null != userContato.getRedesSociais() && !userContato.getRedesSociais().isEmpty()) {

			if (!this.serContato.atualizarRedesSociais(afiliado, userContato.getRedesSociais())) {

				throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
						new Object[] {"redes sociais"}, super.getLocale()));
			}
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Contatos", "atualizados"}, super.getLocale())), HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<RetornoCadastro> cadastrarContato(@PathVariable(value = "id") final Long idUsuario,
			@Valid @RequestBody final UserContato userContato) {

		final Afiliado afiliado = this.repPerfil.findById(idUsuario).orElse(null);

		if (null == afiliado) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Afiliado", "não encontrado"}, super.getLocale()));
		}

		final RetornoCadastro retorno = this.serContato.cadastrarContato(afiliado, userContato);

		if (null == retorno) {

			throw new ParameterNotValidException(
					super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(), new Object[] {"contatos"}, super.getLocale()));
		}

		if (null != userContato.getRedesSociais() && !userContato.getRedesSociais().isEmpty()) {

			this.serContato.atualizarRedesSociais(afiliado, userContato.getRedesSociais());
		}

		return new ResponseEntity<RetornoCadastro>(retorno, HttpStatus.OK);
	}

}
