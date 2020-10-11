package br.com.divulplace.usuario.ws.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages = "br.com.divulplace.usuario.ws")
public class AppConfigWeb extends WebMvcConfigurationSupport {

	/**
	 * Método na arquitetura {@code Spring} responsável pela criação do {@code @Bean} para Locale.
	 *
	 * @return {@link LocaleResolver} com o local da aplicação
	 */
	@Bean
	public LocaleResolver localeResolver() {

		final SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("pt_br"));

		return resolver;
	}

	/**
	 * Método na arquitetura {@code Spring} responsável pela criação do {@code @Bean} para Message.
	 *
	 * @return {@link MessageSource} com as mensagens da aplicação
	 */
	@Bean
	public MessageSource messageSource() {

		final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:/i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	@Override
	public void addCorsMappings(final CorsRegistry registry) {

		registry.addMapping("/**").allowedOrigins("*");
	}

}
