package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.Contato;

/**
 * Classe {@code Repository} para {@link Contato}.
 * 
 * @see CrudRepository
 */
@Repository
public interface ContatoRepository extends CrudRepository<Contato, Long> {

	Optional<Contato> findByAfiliadoIdAfiliado(Long id);

}
