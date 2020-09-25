package br.com.divulplace.usuario.ws.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.divulplace.usuario.entity.Usuario;
import br.com.divulplace.usuario.model.ResponseMessage;
import br.com.divulplace.usuario.model.UserPerfil;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.UsuarioRepository;
import br.com.divulplace.usuario.ws.service.PerfilService;

/**
 * Classe {@code REST} de controle para serviços de Perfil.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/perfil")
public class PerfilController {

	private final Locale locale = new Locale("pt_br");

	@Autowired
	private transient MessageSource sourceMessage;

	@Autowired
	private UsuarioRepository repUsuario;

	@Autowired
	private PerfilService serPerfil;

	@GetMapping("/")
	public String init() {

		return "Perfil Controller.";
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserPerfil> recuperarDadosPessoais(@PathVariable(value = "id") final Long idUsuario) {

		final UserPerfil userPerfil = this.serPerfil.encontrarPerfil(idUsuario);

		if (null == userPerfil) {

			throw new ParameterNotValidException(
					this.sourceMessage.getMessage(MessageProperties.GET_NOT_FOUND.getDescricao(), new Object[] {"perfil"}, locale));
		}

		return new ResponseEntity<UserPerfil>(userPerfil, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarDadosPessoais(@PathVariable(value = "id") final Long idUsuario,
			@Valid @RequestBody final UserPerfil userPerfil) {

		final Usuario usuario = this.repUsuario.findById(idUsuario).orElse(null);

		if (null == usuario) {

			throw new ParameterNotValidException(
					this.sourceMessage.getMessage(MessageProperties.BAD_REQUEST.getDescricao(), new Object[] {"username", "já está em uso"}, locale));
		}

		if (!usuario.getEmail().equals(userPerfil.getEmail()) || !usuario.getNome().equals(userPerfil.getNome())) {

			usuario.setEmail(userPerfil.getEmail());
			usuario.setNome(userPerfil.getNome());
			
			this.repUsuario.save(usuario);
		}

		if (!this.serPerfil.atualizarPerfil(idUsuario, userPerfil)) {

			throw new ParameterNotValidException(
					this.sourceMessage.getMessage(MessageProperties.PUT_CONFLICT.getDescricao(), new Object[] {"perfil"}, locale));
		}

		return new ResponseEntity<>(
				new ResponseMessage(
						this.sourceMessage.getMessage(MessageProperties.PUT_OK.getDescricao(), new Object[] {"Usuário", "atualizado"}, locale)),
				HttpStatus.OK);
	}

}
