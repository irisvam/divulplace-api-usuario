package br.com.divulplace.usuario.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.divulplace.usuario.model.Usuario;
import br.com.divulplace.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@RequestMapping("/")
	public String init() {

		return "Usuario Controller.";
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {

		final List<Usuario> list = service.listAll();

		if (list.isEmpty()) {

			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{usuarioId}")
	public ResponseEntity<Usuario> findById(@PathVariable final Long usuarioId) {

		final Usuario usuario = service.findById(usuarioId);

		if (usuario == null) {

			return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> insert(@Valid @RequestBody final Usuario usuario) {

		if (service.isUsuarioExist(usuario.getEmail())) {

			return new ResponseEntity<Usuario>(HttpStatus.CONFLICT);
		}

		service.save(usuario);

		final HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<Usuario>(usuario, headers, HttpStatus.CREATED);
	}

}
