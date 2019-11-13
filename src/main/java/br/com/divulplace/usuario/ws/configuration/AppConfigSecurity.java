package br.com.divulplace.usuario.ws.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerExceptionResolver;

import br.com.divulplace.usuario.ws.configuration.jwt.JwtAccessDeniedHandler;
import br.com.divulplace.usuario.ws.configuration.jwt.JwtAuthEntryPoint;
import br.com.divulplace.usuario.ws.configuration.jwt.JwtAuthenticationFilter;
import br.com.divulplace.usuario.ws.configuration.jwt.JwtAuthorizationFilter;
import br.com.divulplace.usuario.ws.configuration.jwt.JwtProvider;
import br.com.divulplace.usuario.ws.service.UsuarioService;

/**
 * Classe de Configuração Spring Security para os Beans iniciais.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private transient UsuarioService service;

	@Autowired
	private JwtAuthEntryPoint authEntryHandler;

	@Autowired
	private JwtAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private HandlerExceptionResolver resolver;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}

	/**
	 * Instância do usuário autenticado.
	 *
	 * @return {@link DaoAuthenticationProvider}
	 */
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {

		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(service);

		return authProvider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	/**
	 * Instânciação do encoder para o {@code Password}.
	 *
	 * @return {@link BCryptPasswordEncoder}
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/auth/**").permitAll().anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll().and()
				.addFilter(jwtAuthenticationFilter()).addFilter(jwtAuthorizationFilter()).exceptionHandling()
				.authenticationEntryPoint(authEntryHandler).and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	/**
	 * Criação do filter de Autenticação.
	 *
	 * @return {@link JwtAuthenticationFilter}
	 * @throws Exception para tratamento de erro
	 */
	private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {

		final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtProvider, resolver);

		filter.setAuthenticationManager(authenticationManagerBean());

		return filter;
	}

	/**
	 * Criação do filter de Autorização.
	 *
	 * @return {@link JwtAuthorizationFilter}
	 * @throws Exception para tratamento de erro
	 */
	private JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {

		return new JwtAuthorizationFilter(authenticationManagerBean(), jwtProvider, resolver);
	}

}
