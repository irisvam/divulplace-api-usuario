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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/portfolio")
public class PortfolioServicoController extends CommonController {

	@Autowired
	private PerfilRepository repPerfil;

	@Autowired
	private PortfolioServicoService serServico;

	@GetMapping("/servicos")
	public String init() {

		return "Portfólio Serviço Controller.";
	}

	@GetMapping("/{id}/servicos")
	public ResponseEntity<List<UserPortfolioServico>> recuperarPortfolioServicos(@PathVariable(value = "id") final Long idAfiliado) {

		return new ResponseEntity<List<UserPortfolioServico>>(this.serServico.encontrarListaPortfolioServicos(idAfiliado), HttpStatus.OK);
	}

	@PostMapping("/{id}/servicos")
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

	@GetMapping("/servicos/{id}")
	public ResponseEntity<UserPortfolioServico> recuperarPortfolioServico(@PathVariable(value = "id") final Long idServico) {

		final UserPortfolioServico userServico = this.serServico.encontrarServico(idServico);

		if (null == userServico) {

			throw new ParameterNotValidException(
					super.getSourceMessage().getMessage(MessageProperties.GET_NOT_FOUND.getDescricao(), new Object[] {"serviço"}, super.getLocale()));
		}

		return new ResponseEntity<UserPortfolioServico>(userServico, HttpStatus.OK);
	}

	@PutMapping("/servicos/{id}")
	public ResponseEntity<?> atualizarPortfolioServico(@PathVariable(value = "id") final Long idServico,
			@Valid @RequestBody final UserPortfolioServico userServico) {

		if (!this.serServico.atualizarPortfolioServico(idServico, userServico)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
					new Object[] {"serviço do portfolio"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Serviço", "atualizado"}, super.getLocale())), HttpStatus.OK);
	}

	@DeleteMapping("/servicos/{id}")
	public ResponseEntity<?> deletarPortfolioServico(@PathVariable(value = "id") final Long idServico) {

		if (!this.serServico.deletarServico(idServico)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Serviço", "não encontrado"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Serviço", "deletado"}, super.getLocale())), HttpStatus.OK);
	}

	@PostMapping("/servicos/{id}/imagens")
	public ResponseEntity<?> adicionarImagensPortfolioServico(@PathVariable(value = "id") final Long idServico,
			@RequestParam final MultipartFile[] files) {

		System.out.println(files.length);

		for (final MultipartFile image : files) {

			System.out.println(image.getName());
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Imagens do Serviço", "atualizado"}, super.getLocale())), HttpStatus.OK);
	}

}
