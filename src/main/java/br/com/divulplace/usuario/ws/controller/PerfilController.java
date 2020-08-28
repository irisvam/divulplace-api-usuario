package br.com.divulplace.usuario.ws.controller;

import java.util.Date;
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

import br.com.divulplace.usuario.model.ResponseMessage;
import br.com.divulplace.usuario.model.UserPerfil;
import br.com.divulplace.usuario.util.enums.MessageProperties;

/**
 * Classe {@code REST} de controle para serviços de usuários.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/usuarios")
public class PerfilController {

	private final Locale locale = new Locale("pt_br");

	@Autowired
	private transient MessageSource sourceMessage;

	@GetMapping("/")
	public String init() {

		return "Perfil Controller.";
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserPerfil> recuperarDadosPessoais(@PathVariable(value = "id") final Integer idUsuario) {

		final UserPerfil userPerfil = new UserPerfil();
		userPerfil.setId(idUsuario);
		userPerfil.setCodigo("2018ACDAKL8765");
		userPerfil.setTratamento("Sr.");
		userPerfil.setImg("assets/img/user.png");
		userPerfil.setNome("Nome do Usuário");
		userPerfil.setApelido("User2019");
		userPerfil.setCpf("12345678909");
		userPerfil.setDataNascimento(new Date());
		userPerfil.setEstadoCivil("CASADO");
		userPerfil.setSexo("M");
		userPerfil.setSobre("Conhecido");
		userPerfil.setEmail("usuario@email.com");
		userPerfil.setLink("usuario2019novo");

		return new ResponseEntity<UserPerfil>(userPerfil, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarDadosPessoais(@PathVariable(value = "id") final Integer idUsuario,
			@Valid @RequestBody final UserPerfil userPerfil) {

		userPerfil.getId();
		userPerfil.getNome();

		return new ResponseEntity<>(
				new ResponseMessage(
						sourceMessage.getMessage(MessageProperties.PUT_OK.getDescricao(), new Object[] {"Usuário", "atualizado"}, locale)),
				HttpStatus.OK);
	}

}
