package br.com.divulplace.usuario.handler;

import java.util.Date;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.divulplace.usuario.exception.ParameterNotValidException;
import br.com.divulplace.usuario.exception.ResourceConflictException;
import br.com.divulplace.usuario.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Método para tratar dos erros de {@code HttpStatus.NOT_FOUND} dos serviços deste {@code WebService}.
	 *
	 * @param rnfException um {@link ResourceNotFoundException} quando enviado com a mensagem de erro
	 * @return {@link ResponseEntity} com a resposta {@code HTTP}
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(final ResourceNotFoundException rnfException) {

		final ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(HttpStatus.NOT_FOUND.value())
				.title("Recurso não encontrado!").detail(rnfException.getMessage()).devMessage(rnfException.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/**
	 * Método para tratar dos erros de {@code HttpStatus.BAD_REQUEST} dos serviços deste {@code WebService}.
	 *
	 * @param rnfException um {@link ParameterNotValidException} quando enviado com a mensagem de erro
	 * @return {@link ResponseEntity} com a resposta {@code HTTP}
	 */
	@ExceptionHandler(ParameterNotValidException.class)
	public ResponseEntity<?> handlerParamNotValidException(final ParameterNotValidException rnfException) {

		final ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(HttpStatus.BAD_REQUEST.value())
				.title("Parâmetro não aceitável!").detail(rnfException.getMessage()).devMessage(rnfException.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Método para tratar dos erros de {@code HttpStatus.BAD_REQUEST} dos serviços deste {@code WebService}.
	 *
	 * @param rnfException um {@link MethodArgumentTypeMismatchException} quando enviado com a mensagem de erro
	 * @return {@link ResponseEntity} com a resposta {@code HTTP}
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handlerMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException rnfException) {

		String typeEsperado = null;
		final StringBuilder valorErrado = new StringBuilder();

		if (rnfException.getRequiredType().getName().equals("java.util.List")) {

			typeEsperado = rnfException.getParameter().getGenericParameterType().getTypeName().replaceAll("java.util.List", "");
			final String[] arrEsperado = (String[]) rnfException.getValue();
			valorErrado.append("[");
			for (int index = 0; index < arrEsperado.length; index++) {
				if (index != 0) {
					valorErrado.append(",");
				}
				valorErrado.append(arrEsperado[index]);
			}
			valorErrado.append("]");
		} else {

			typeEsperado = rnfException.getRequiredType().getName();
			valorErrado.append(rnfException.getValue());
		}

		final ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(HttpStatus.BAD_REQUEST.value())
				.title("Valor de parâmetro não esperado!").detail("Falha ao tentar converter valor(es) '" + valorErrado.toString()
						+ "' do parâmetro '" + rnfException.getName() + "' para o tipo '" + typeEsperado + "' esperado.")
				.devMessage(rnfException.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Método para tratar dos erros de {@code HttpStatus.CONFLICT} dos serviços deste {@code WebService}.
	 *
	 * @param rnfException um {@link ResourceConflictException} quando enviado com a mensagem de erro
	 * @return {@link ResponseEntity} com a resposta {@code HTTP}
	 */
	@ExceptionHandler(ResourceConflictException.class)
	public ResponseEntity<?> handlerResourceConflictException(final ResourceConflictException rnfException) {

		final ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(HttpStatus.CONFLICT.value())
				.title("Informação conflitante!").detail(rnfException.getMessage()).devMessage(rnfException.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException msrpException,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

		final ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(HttpStatus.BAD_REQUEST.value())
				.title("Parâmetro requerido não recebido!").detail("Parâmetro '" + msrpException.getParameterName() + "' está faltando.")
				.devMessage(msrpException.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException manvException, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {

		final List<FieldError> lista = manvException.getBindingResult().getFieldErrors();

		final StringBuilder fildMessage = new StringBuilder();

		for (int index = 0; index < lista.size(); index++) {

			if (index > 0) {

				fildMessage.append(" | ");
			}

			fildMessage.append("campo:").append(lista.get(index).getField()).append(" - ").append(lista.get(index).getDefaultMessage());
		}

		final ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(HttpStatus.BAD_REQUEST.value())
				.title("Erro de validação de campo!").detail(fildMessage.toString()).devMessage(manvException.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(final Exception exception, final Object body, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {

		final ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new Date().getTime()).status(status.value())
				.title("Erro na requisição interna!").detail(exception.getMessage()).devMessage(exception.getClass().getName()).build();

		return new ResponseEntity<>(errorDetails, headers, status);
	}

}
