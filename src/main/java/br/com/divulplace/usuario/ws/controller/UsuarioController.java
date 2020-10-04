package br.com.divulplace.usuario.ws.controller;

import java.util.Date;
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

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.entity.Role;
import br.com.divulplace.usuario.entity.Usuario;
import br.com.divulplace.usuario.model.ResponseMessage;
import br.com.divulplace.usuario.model.SignUpForm;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.util.enums.RoleName;
import br.com.divulplace.usuario.util.enums.TipoSituacao;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;
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
	private PerfilRepository repPerfil;

	@Autowired
	private RoleRepository repRole;

	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping("/")
	public String init() {

		return "Usuário Controller.";
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody final SignUpForm signUpRequest) {

		if (this.repUsuario.existsByLogin(signUpRequest.getUsername())) {

			throw new ParameterNotValidException(
					this.sourceMessage.getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
							new Object[] { "username", "já está em uso" }, locale));
		}

		final Usuario user = new Usuario(signUpRequest.getUsername(), this.encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getName(), signUpRequest.getEmail(), TipoSituacao.CADASTRADO);

		user.setRoles(this.criarRoles(signUpRequest.getRole()));
		user.setDtaCadastro(new Date());

		final Afiliado afiliado = new Afiliado();
		afiliado.setDesNome(user.getNome());
		afiliado.setDesEmail(user.getEmail());
		afiliado.setUsuario(this.repUsuario.save(user));

		this.repPerfil.save(afiliado);

		return new ResponseEntity<>(new ResponseMessage("Usuário registrado com Sucesso!"), HttpStatus.OK);
	}

	/**
	 * Mértodo para criação das {@code ROLES} do usuário a partir de uma lista
	 * recebida.
	 *
	 * @param signUpRole {@code SET<}{@link RoleName}{@code >}
	 * @return {@code Set<}{@link Role}{@code >}
	 */
	private Set<Role> criarRoles(final Set<RoleName> signUpRole) {

		final Set<Role> roles = new HashSet<Role>();

		if (null == signUpRole || signUpRole.isEmpty()) {

			roles.add(this.criarUserRule());

		} else {

			signUpRole.forEach(role -> {

				switch (role) {
				case ROLE_ADMIN:

					final Role adminRole = this.repRole.findByNome(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> this.criarNotValidException());
					roles.add(adminRole);

					break;
				default:

					roles.add(this.criarUserRule());
					break;
				}
			});
		}

		return roles;
	}

	/**
	 * Método para criar um papel de {@code USER} para o usuário.
	 *
	 * @return {@link Role}
	 */
	private Role criarUserRule() {

		return this.repRole.findByNome(RoleName.ROLE_USER).orElseThrow(() -> this.criarNotValidException());
	}

	/**
	 * Método para criar uma exceção de perfil não existe.
	 *
	 * @return {@link ParameterNotValidException}
	 */
	private ParameterNotValidException criarNotValidException() {

		return new ParameterNotValidException(this.sourceMessage.getMessage(
				MessageProperties.BAD_REQUEST.getDescricao(), new Object[] { "role", "não existente" }, locale));
	}

}
