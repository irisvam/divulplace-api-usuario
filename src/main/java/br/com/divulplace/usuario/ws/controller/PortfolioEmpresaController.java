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
import br.com.divulplace.usuario.model.UserPortfolioEmpresa;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;
import br.com.divulplace.usuario.ws.service.PortfolioEmpresaService;

/**
 * Classe {@code REST} de controle para serviços de Empresa do Portfólio.
 */
@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/portfolio")
public class PortfolioEmpresaController extends CommonController {

	@Autowired
	private PerfilRepository repPerfil;

	@Autowired
	private PortfolioEmpresaService serEmpresa;

	@GetMapping("/empresas")
	public String init() {

		return "Portfólio Empresas Controller.";
	}

	@GetMapping("/{id}/empresas")
	public ResponseEntity<List<UserPortfolioEmpresa>> recuperarPortfolioEmpresas(@PathVariable(value = "id") final Long idAfiliado) {

		return new ResponseEntity<List<UserPortfolioEmpresa>>(this.serEmpresa.encontrarListaPortfolioEmpresas(idAfiliado), HttpStatus.OK);
	}

	@PostMapping("/{id}/empresas")
	public ResponseEntity<RetornoCadastro> cadastrarPortfolioEmpresa(@PathVariable(value = "id") final Long idAfiliado,
			@Valid @RequestBody final UserPortfolioEmpresa userEmpresa) {

		final Afiliado afiliado = this.repPerfil.findById(idAfiliado).orElse(null);

		if (null == afiliado) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Afiliado", "não encontrado"}, super.getLocale()));
		}

		final RetornoCadastro retorno = this.serEmpresa.cadastrarPortfolioEmpresa(afiliado, userEmpresa);

		if (null == retorno) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
					new Object[] {"empresa do portfolio"}, super.getLocale()));
		}

		return new ResponseEntity<RetornoCadastro>(retorno, HttpStatus.OK);
	}

	@GetMapping("/empresas/{id}")
	public ResponseEntity<UserPortfolioEmpresa> recuperarPortfolioProduto(@PathVariable(value = "id") final Long idEmpresa) {

		final UserPortfolioEmpresa userEmpresa = this.serEmpresa.encontrarEmpresa(idEmpresa);

		if (null == userEmpresa) {

			throw new ParameterNotValidException(
					super.getSourceMessage().getMessage(MessageProperties.GET_NOT_FOUND.getDescricao(), new Object[] {"empresa"}, super.getLocale()));
		}

		return new ResponseEntity<UserPortfolioEmpresa>(userEmpresa, HttpStatus.OK);
	}

	@PutMapping("/empresas/{id}")
	public ResponseEntity<?> atualizarPortfolioEmpresa(@PathVariable(value = "id") final Long idEmpresa,
			@Valid @RequestBody final UserPortfolioEmpresa userEmpresa) {

		if (!this.serEmpresa.atualizarPortfolioEmpresa(idEmpresa, userEmpresa)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
					new Object[] {"empresa do portfolio"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Empresa", "atualizada"}, super.getLocale())), HttpStatus.OK);
	}

	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<?> deletarPortfolioServico(@PathVariable(value = "id") final Long idEmpresa) {

		if (!this.serEmpresa.deletarEmpresa(idEmpresa)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Empresa", "não encontrada"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Empresa", "deletada"}, super.getLocale())), HttpStatus.OK);
	}

	@PostMapping("/empresas/{id}/imagens")
	public ResponseEntity<?> adicionarImagensPortfolioProduto(@PathVariable(value = "id") final Long idEmpresa,
			@RequestParam final MultipartFile[] files) {

		System.out.println(files.length);

		for (final MultipartFile image : files) {

			System.out.println(image.getName());
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Imagens da Empresa", "atualizado"}, super.getLocale())), HttpStatus.OK);
	}

}
