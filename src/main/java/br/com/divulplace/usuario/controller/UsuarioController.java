package br.com.divulplace.usuario.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.divulplace.usuario.exception.ResourceConflictException;
import br.com.divulplace.usuario.model.Usuario;
import br.com.divulplace.usuario.service.UsuarioService;
import br.com.divulplace.usuario.util.enums.MessageProperties;

/**
 * Classe {@code REST} de controle para serviços de usuários.
 */
@CrossOrigin
@RestController
@Validated
@RequestMapping("/usuarios")
public class UsuarioController {

	private final Locale locale = new Locale("pt_br");

	@Autowired
	private transient MessageSource sourceMessage;

	@Autowired
	private transient UsuarioService service;

	@RequestMapping("/")
	public String init() {

		return "Usuario Controller.";
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {

		final List<Usuario> lista = service.listAll();

		final HttpHeaders headers = new HttpHeaders();

		if (lista.isEmpty()) {

			headers.set(MessageProperties.MESSAGE.getDescricao(),
					sourceMessage.getMessage(MessageProperties.GET_NO_CONTENT.getDescricao(), new Object[] {"usuario"}, locale));
		}

		headers.set(MessageProperties.MESSAGE.getDescricao(),
				sourceMessage.getMessage(MessageProperties.GET_LIST_OK.getDescricao(), new Object[] {"lei"}, locale));

		return new ResponseEntity<List<Usuario>>(lista, headers, HttpStatus.OK);
	}

	@GetMapping(value = "/{usuarioId}")
	public ResponseEntity<Usuario> findById(@PathVariable final Long usuarioId) {

		final Usuario usuario = service.findById(usuarioId);

		final HttpHeaders headers = new HttpHeaders();

		if (usuario == null) {

			headers.set(MessageProperties.MESSAGE.getDescricao(),
					sourceMessage.getMessage(MessageProperties.GET_NO_CONTENT.getDescricao(), new Object[] {"usuario"}, locale));
		}

		headers.set(MessageProperties.MESSAGE.getDescricao(),
				sourceMessage.getMessage(MessageProperties.GET_OK.getDescricao(), new Object[] {"lei"}, locale));

		return new ResponseEntity<Usuario>(usuario, headers, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> insert(@Valid @RequestBody final Usuario usuario) {

		final HttpHeaders headers = new HttpHeaders();

		if (service.isUsuarioExist(usuario.getEmail())) {

			throw new ResourceConflictException("Usuário já cadastro com este e-mail no sistema.");
		}

		service.save(usuario);

		return new ResponseEntity<Usuario>(usuario, headers, HttpStatus.CREATED);
	}

}
