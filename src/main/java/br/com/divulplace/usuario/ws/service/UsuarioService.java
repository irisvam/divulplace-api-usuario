package br.com.divulplace.usuario.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Usuario;
import br.com.divulplace.usuario.model.UserPrinciple;
import br.com.divulplace.usuario.ws.repositories.UsuarioRepository;

/**
 * Classe {@code Service} para formatação de dados do Usuário.
 * 
 * @see UserDetailsService
 */
@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repUsuario;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		final Usuario user = this.repUsuario.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado -> login or email : " + username));

		return UserPrinciple.build(user);
	}

}
