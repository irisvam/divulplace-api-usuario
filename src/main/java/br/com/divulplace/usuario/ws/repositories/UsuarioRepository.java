package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.Usuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);

	Boolean existsByLogin(String login);

	Boolean existsByEmail(String email);

}
