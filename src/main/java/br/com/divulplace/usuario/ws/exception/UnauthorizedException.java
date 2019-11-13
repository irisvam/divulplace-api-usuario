package br.com.divulplace.usuario.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe {@code Exception} que {@code extends} {@link RuntimeException} para serviços que retornem um
 * {@code HttpStatus.UNAUTHORIZED}.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 3677379698771978855L;

	/**
	 * Construtor padrão para essa excessão.
	 *
	 * @param message {@code String} com a mensagem desejada
	 */
	public UnauthorizedException(final String message) {

		super(message);
	}

}
