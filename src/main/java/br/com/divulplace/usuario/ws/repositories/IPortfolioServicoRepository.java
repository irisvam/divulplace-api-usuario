package br.com.divulplace.usuario.ws.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.PortfolioServico;

/**
 * Classe {@code Repository} para {@link PortfolioServico}.
 * 
 * @see CrudRepository
 */
@Repository
public interface IPortfolioServicoRepository extends CrudRepository<PortfolioServico, Long> {

	Set<PortfolioServico> findAllByAfiliadoIdAfiliado(Long id);

}
