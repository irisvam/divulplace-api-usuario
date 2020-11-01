package br.com.divulplace.usuario.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.divulplace.usuario.model.UserRedeSocial;
import br.com.divulplace.usuario.ws.service.RedeSocialService;

/**
 * Classe {@code REST} de controle para serviços de Rede Social.
 */
@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/social")
public class RedeSocialController {

	@Autowired
	private RedeSocialService serSocial;

	@GetMapping("/{id}")
	public ResponseEntity<List<UserRedeSocial>> recuperarContato(@PathVariable(value = "id") final Long idAfiliado) {

		return new ResponseEntity<List<UserRedeSocial>>(this.serSocial.encontrarListaRedesSociais(idAfiliado), HttpStatus.OK);
	}

}
