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
import br.com.divulplace.usuario.model.UserPortfolioProduto;
import br.com.divulplace.usuario.util.enums.MessageProperties;
import br.com.divulplace.usuario.ws.exception.ParameterNotValidException;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;
import br.com.divulplace.usuario.ws.service.PortfolioProdutoService;

/**
 * Classe {@code REST} de controle para serviços dos Produtos de Portfólio.
 */
@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/portfolio")
public class PortfolioProdutoController extends CommonController {

	@Autowired
	private PerfilRepository repPerfil;

	@Autowired
	private PortfolioProdutoService serProduto;

	@GetMapping("/produtos")
	public String init() {

		return "Portfólio Produtos Controller.";
	}

	@GetMapping("/{id}/produtos")
	public ResponseEntity<List<UserPortfolioProduto>> recuperarPortfolioProdutos(@PathVariable(value = "id") final Long idAfiliado) {

		return new ResponseEntity<List<UserPortfolioProduto>>(this.serProduto.encontrarListaPortfolioProdutos(idAfiliado), HttpStatus.OK);
	}

	@PostMapping("/{id}/produtos")
	public ResponseEntity<RetornoCadastro> cadastrarPortfolioProduto(@PathVariable(value = "id") final Long idAfiliado,
			@Valid @RequestBody final UserPortfolioProduto userProduto) {

		final Afiliado afiliado = this.repPerfil.findById(idAfiliado).orElse(null);

		if (null == afiliado) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Afiliado", "não encontrado"}, super.getLocale()));
		}

		final RetornoCadastro retorno = this.serProduto.cadastrarPortfolioProduto(afiliado, userProduto);

		if (null == retorno) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
					new Object[] {"produto do portfolio"}, super.getLocale()));
		}

		return new ResponseEntity<RetornoCadastro>(retorno, HttpStatus.OK);
	}

	@GetMapping("/produtos/{id}")
	public ResponseEntity<UserPortfolioProduto> recuperarPortfolioProduto(@PathVariable(value = "id") final Long idProduto) {

		final UserPortfolioProduto userProduto = this.serProduto.encontrarProduto(idProduto);

		if (null == userProduto) {

			throw new ParameterNotValidException(
					super.getSourceMessage().getMessage(MessageProperties.GET_NOT_FOUND.getDescricao(), new Object[] {"produto"}, super.getLocale()));
		}

		return new ResponseEntity<UserPortfolioProduto>(userProduto, HttpStatus.OK);
	}

	@PutMapping("/produtos/{id}")
	public ResponseEntity<?> atualizarPortfolioProduto(@PathVariable(value = "id") final Long idProduto,
			@Valid @RequestBody final UserPortfolioProduto userProduto) {

		if (!this.serProduto.atualizarPortfolioProduto(idProduto, userProduto)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.PUT_CONFLICT.getDescricao(),
					new Object[] {"produto do portfolio"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Produto", "atualizado"}, super.getLocale())), HttpStatus.OK);
	}

	@DeleteMapping("/produtos/{id}")
	public ResponseEntity<?> deletarPortfolioServico(@PathVariable(value = "id") final Long idProduto) {

		if (!this.serProduto.deletarProduto(idProduto)) {

			throw new ParameterNotValidException(super.getSourceMessage().getMessage(MessageProperties.BAD_REQUEST.getDescricao(),
					new Object[] {"Produto", "não encontrado"}, super.getLocale()));
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Produto", "deletado"}, super.getLocale())), HttpStatus.OK);
	}

	@PostMapping("/produtos/{id}/imagens")
	public ResponseEntity<?> adicionarImagensPortfolioProduto(@PathVariable(value = "id") final Long idProduto,
			@RequestParam final MultipartFile[] files) {

		System.out.println(files.length);

		for (final MultipartFile image : files) {

			System.out.println(image.getName());
		}

		return new ResponseEntity<>(new ResponseMessage(super.getSourceMessage().getMessage(MessageProperties.PUT_OK.getDescricao(),
				new Object[] {"Imagens do Produto", "atualizado"}, super.getLocale())), HttpStatus.OK);
	}

}
