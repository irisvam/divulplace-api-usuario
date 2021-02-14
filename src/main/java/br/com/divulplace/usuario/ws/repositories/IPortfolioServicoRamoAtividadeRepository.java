package br.com.divulplace.usuario.ws.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.PortfolioServicoRamoAtividade;
import br.com.divulplace.usuario.entity.PortfolioServicoRamoAtividadeId;

/**
 * Classe {@code Repository} para {@link PortfolioServicoRamoAtividade}.
 * 
 * @see CrudRepository
 */
@Repository
public interface IPortfolioServicoRamoAtividadeRepository extends CrudRepository<PortfolioServicoRamoAtividade, PortfolioServicoRamoAtividadeId> {

}
