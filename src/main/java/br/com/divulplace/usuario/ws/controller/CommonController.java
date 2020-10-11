package br.com.divulplace.usuario.ws.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Classe com atributos comuns às classes {@code Controllers}.
 */
public class CommonController {

	private final Locale locale = new Locale("pt_br");

	@Autowired
	private transient MessageSource sourceMessage;

	public MessageSource getSourceMessage() {

		return sourceMessage;
	}

	public void setSourceMessage(final MessageSource sourceMessage) {

		this.sourceMessage = sourceMessage;
	}

	public Locale getLocale() {

		return locale;
	}

}
