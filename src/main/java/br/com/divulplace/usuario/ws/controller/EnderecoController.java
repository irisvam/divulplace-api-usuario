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
import br.com.divulplace.usuario.model.UserEndereco;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;
import br.com.divulplace.usuario.ws.service.EnderecoService;

/**
 * Classe {@code REST} de controle para serviços de Endereço.
 */
@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/endereco")
public class EnderecoController extends CommonController {

	@Autowired
	private EnderecoService serEndereco;

	@Autowired
	private PerfilRepository repPerfil;

	@GetMapping("/{id}")
	public ResponseEntity<UserEndereco> recuperarEndereco(@PathVariable(value = "id") final Long idAfiliado) {

		final UserEndereco userEndereco = this.serEndereco.encontrarEnderecoAfiliado(idAfiliado);

		if (null == userEndereco) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.GET_NOT_FOUND.getDescricao(),
					new Object[] {"endereço"}, super.getLocale()));
		}

		return new ResponseEntity<UserEndereco>(userEndereco, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarEndereco(@PathVariable(value = "id") final Long idUsuario,
			@Valid @RequestBody final UserEndereco userEndereco) {

		final Afiliado afiliado = this.repPerfil.findById(idUsuario).orElse(null);

		if (null == afiliado) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Afiliado", "não encontrado"}, super.getLocale()));
		}

		if (!this.serEndereco.atualizarEndereco(userEndereco)) {

			throw new ParameterNotValidException(
					super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(), new Object[] {"perfil"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Endereço", "atualizado"}, super.getLocale())), HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<RetornoCadastro> cadastrarEndereco(@PathVariable(value = "id") final Long idUsuario,
			@Valid @RequestBody final UserEndereco userEndereco) {

		final Afiliado afiliado = this.repPerfil.findById(idUsuario).orElse(null);

		if (null == afiliado) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Afiliado", "não encontrado"}, super.getLocale()));
		}

		final RetornoCadastro retorno = this.serEndereco.cadastrarEndereco(afiliado, userEndereco);

		if (null == retorno) {

			throw new ParameterNotValidException(
					super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(), new Object[] {"perfil"}, super.getLocale()));
		}

		return new ResponseEntity<RetornoCadastro>(retorno, HttpStatus.OK);
	}

}
