package br.com.divulplace.usuario.ws.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import br.com.divulplace.usuario.model.UserPortfolioServico;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;
import br.com.divulplace.usuario.ws.service.PortfolioServicoService;

/**
 * Classe {@code REST} de controle para serviços dos Serviços de Portfólio.
 */
@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/portfolio/servico")
public class PortfolioServicoController extends CommonController {

	@Autowired
	private PerfilRepository repPerfil;

	@Autowired
	private PortfolioServicoService serServico;

	@GetMapping("/")
	public String init() {

		return "Portfólio Serviço Controller.";
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<UserPortfolioServico>> recuperarPortfolioServico(@PathVariable(value = "id") final Long idAfiliado) {

		return new ResponseEntity<List<UserPortfolioServico>>(this.serServico.encontrarListaPortfolioServicos(idAfiliado), HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<RetornoCadastro> cadastrarPortfolioServico(@PathVariable(value = "id") final Long idUsuario,
			@Valid @RequestBody final UserPortfolioServico userServico) {

		final Afiliado afiliado = this.repPerfil.findById(idUsuario).orElse(null);

		if (null == afiliado) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Afiliado", "não encontrado"}, super.getLocale()));
		}

		final RetornoCadastro retorno = this.serServico.cadastrarPortfolioServico(afiliado, userServico);

		if (null == retorno) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
					new Object[] {"serviço do portfolio"}, super.getLocale()));
		}

		return new ResponseEntity<RetornoCadastro>(retorno, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarPortfolioServico(@PathVariable(value = "id") final Long idService,
			@Valid @RequestBody final UserPortfolioServico userServico) {

		if (!this.serServico.atualizarPortfolioServico(idService, userServico)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
					new Object[] {"serviço do portfolio"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Serviço", "atualizado"}, super.getLocale())), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> atualizarPortfolioServico(@PathVariable(value = "id") final Long idService) {

		if (!this.serServico.deletarServico(idService)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Serviço", "não encontrado"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Serviço", "deletado"}, super.getLocale())), HttpStatus.OK);
	}

}
