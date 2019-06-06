package br.com.divulplace.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.model.Usuario;
import br.com.divulplace.usuario.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repUsuario;

	public Usuario save(final Usuario usuario) {

		return repUsuario.save(usuario);
	}

	public Usuario findById(final Long id) {

		Optional<Usuario> usuario = repUsuario.findById(id);

		Usuario resultado = null;

		if (usuario.isPresent()) {

			resultado = usuario.get();
		}

		return resultado;
	}

	public List<Usuario> listAll() {

		return (List<Usuario>) repUsuario.findAll();
	}

	public boolean isUsuarioExist(final String email) {

		List<Usuario> lista = repUsuario.findByEmail(email);

		if (null == lista || lista.isEmpty()) {

			return false;
		}

		return true;
	}

}
