package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.RamoAtividade;

/**
 * Classe {@code Repository} para {@link RamoAtividade}.
 * 
 * @see CrudRepository
 */
@Repository
public interface IRamoAtividadeRepository extends CrudRepository<RamoAtividade, Long> {

	Optional<RamoAtividade> findByNomRamo(String nomRamo);

}
