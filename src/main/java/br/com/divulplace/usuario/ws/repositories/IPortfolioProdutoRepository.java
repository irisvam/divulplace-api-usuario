package br.com.divulplace.usuario.ws.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.PortfolioProduto;

/**
 * Classe {@code Repository} para {@link PortfolioProduto}.
 * 
 * @see CrudRepository
 */
@Repository
public interface IPortfolioProdutoRepository extends CrudRepository<PortfolioProduto, Long>{

	Set<PortfolioProduto> findAllByAfiliadoIdAfiliado(Long id);

	Optional<PortfolioProduto> findByIdProduto(Long idProduto);

}
