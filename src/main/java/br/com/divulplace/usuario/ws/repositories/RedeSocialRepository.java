package br.com.divulplace.usuario.ws.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.RedeSocial;

/**
 * Classe {@code Repository} para {@link RedeSocial}.
 * 
 * @see CrudRepository
 */
@Repository
public interface RedeSocialRepository extends CrudRepository<RedeSocial, Long> {

	Set<RedeSocial> findAllByAfiliadoIdAfiliado(Long id);

}
