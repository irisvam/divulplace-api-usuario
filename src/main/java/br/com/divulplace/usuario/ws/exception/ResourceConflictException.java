package br.com.divulplace.usuario.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe {@code Exception} que {@code extends} {@link RuntimeException} para serviços que retornem um
 * {@code HttpStatus.CONFLICT}.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceConflictException extends RuntimeException {

	private static final long serialVersionUID = 7212412449268925116L;

	/**
	 * Construtor padrão para essa excessão.
	 *
	 * @param message {@code String} com a mensagem desejada
	 */
	public ResourceConflictException(String message) {

		super(message);
	}

}
