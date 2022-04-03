package br.com.divulplace.usuario.ws.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.divulplace.usuario.entity.PortfolioEmpresaRamoAtividade;
import br.com.divulplace.usuario.entity.PortfolioEmpresaRamoAtividadeId;

/**
 * Classe {@code Repository} para {@link PortfolioEmpresaRamoAtividade}.
 * 
 * @see CrudRepository
 */
@Repository
public interface IPortfolioEmpresaRamoAtividadeRepository extends CrudRepository<PortfolioEmpresaRamoAtividade, PortfolioEmpresaRamoAtividadeId> {

}
