package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.Afiliado;

@Repository
public interface PerfilRepository extends CrudRepository<Afiliado, Long> {

	Optional<Afiliado> findByUsuarioId(Long id);

}
