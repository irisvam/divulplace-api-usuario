package br.com.divulplace.usuario.ws.controller;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.divulplace.usuario.entity.Role;
import br.com.divulplace.usuario.entity.Usuario;
import br.com.divulplace.usuario.model.ResponseMessage;
import br.com.divulplace.usuario.model.SignUpForm;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.util.enums.RoleName;
import br.com.divulplace.usuario.util.enums.TipoSituacao;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.RoleRepository;
import br.com.divulplace.usuario.ws.repositories.UsuarioRepository;

/**
 * Classe {@code REST} de controle para serviços de usuários.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/auth")
public class UsuarioController {

	private final Locale locale = new Locale("pt_br");

	@Autowired
	private transient MessageSource sourceMessage;

	@Autowired
	private UsuarioRepository repUsuario;

	@Autowired
	private RoleRepository repRole;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping("/")
	public String init() {

		return "Usuario Controller.";
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody final SignUpForm signUpRequest) {

		if (repUsuario.existsByLogin(signUpRequest.getUsername())) {

			throw new ParameterNotValidException(
					sourceMessage.getMessage(MessageProperties.BAD_REQUEST.getDescricao(), new Object[] {"username", "já está em uso"}, locale));
		}

		// Creating user's account
		final Usuario user = new Usuario(signUpRequest.getUsername(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getName(),
				signUpRequest.getEmail(), TipoSituacao.CADASTRADO);

		final Set<Role> roles = new HashSet<Role>();

		if(null == signUpRequest.getRole() || signUpRequest.getRole().isEmpty()) {

			final Role userRole = repRole.findByNome(RoleName.ROLE_USER).orElseThrow(() -> new ParameterNotValidException(sourceMessage
					.getMessage(MessageProperties.BAD_REQUEST.getDescricao(), new Object[] {"perfil", "não existente"}, locale)));
			roles.add(userRole);

		} else {

			signUpRequest.getRole().forEach(role -> {
				switch (role) {
					case ROLE_ADMIN:
						final Role adminRole = repRole.findByNome(RoleName.ROLE_ADMIN).orElseThrow(() -> new ParameterNotValidException(sourceMessage
								.getMessage(MessageProperties.BAD_REQUEST.getDescricao(), new Object[] {"perfil", "não existente"}, locale)));
						roles.add(adminRole);
	
						break;
					default:
						final Role userRole = repRole.findByNome(RoleName.ROLE_USER).orElseThrow(() -> new ParameterNotValidException(sourceMessage
								.getMessage(MessageProperties.BAD_REQUEST.getDescricao(), new Object[] {"perfil", "não existente"}, locale)));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		repUsuario.save(user);

		return new ResponseEntity<>(new ResponseMessage("Usuário registrado com Sucesso!"), HttpStatus.OK);
	}

}
