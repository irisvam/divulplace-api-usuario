package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.Endereco;

/**
 * Classe {@code Repository} para {@link Endereco}.
 * 
 * @see CrudRepository
 */
@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

	Optional<Endereco> findByAfiliadoIdAfiliado(Long id);

}
