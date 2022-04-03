package br.com.divulplace.usuario.ws.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.PortfolioEmpresa;

/**
 * Classe {@code Repository} para {@link PortfolioEmpresa}.
 * 
 * @see CrudRepository
 */
@Repository
public interface IPortfolioEmpresaRepository extends CrudRepository<PortfolioEmpresa, Long> {

	Set<PortfolioEmpresa> findAllByAfiliadoIdAfiliado(Long id);

	@Query("delete from PortfolioServicoRamoAtividade tb where tb.id.idServico = :idServico and tb.id.idRamo = :idRamo ")
	int deleteById(Long idServico, Long idRamo);

}
