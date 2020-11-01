package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.Afiliado;

/**
 * Classe {@code Repository} para {@link Afiliado}.
 * 
 * @see CrudRepository
 */
@Repository
public interface PerfilRepository extends CrudRepository<Afiliado, Long> {

	Optional<Afiliado> findByUsuarioId(Long id);

}
