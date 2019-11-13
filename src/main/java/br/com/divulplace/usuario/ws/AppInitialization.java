package br.com.divulplace.usuario.ws;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.divulplace.usuario.ws.configuration.AppConfigWeb;

/**
 * Classe que {@code extends} a classe {@link AbstractAnnotationConfigDispatcherServletInitializer} para inicialização
 * do sistema com Spring.
 */
public class AppInitialization extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] {AppConfigWeb.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] {"/"};
	}

}
