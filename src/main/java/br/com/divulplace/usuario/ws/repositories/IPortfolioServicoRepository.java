package br.com.divulplace.usuario.ws.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
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

	@Query("delete from PortfolioServicoRamoAtividade tb where tb.id.idServico = :idServico and tb.id.idRamo = :idRamo ")
	int deleteById(Long idServico, Long idRamo);

}
