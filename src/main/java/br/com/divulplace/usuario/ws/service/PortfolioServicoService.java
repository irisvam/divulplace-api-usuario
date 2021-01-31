package br.com.divulplace.usuario.ws.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.entity.PortfolioServico;
import br.com.divulplace.usuario.entity.RamoAtividade;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserPortfolioServico;
import br.com.divulplace.usuario.model.UserRamoAtividade;
import br.com.divulplace.usuario.ws.repositories.IPortfolioServicoRepository;
import br.com.divulplace.usuario.ws.repositories.IRamoAtividadeRepository;

/**
 * Classe {@code Service} para formatação de dados de Serviços do Portfólio.
 */
@Service
public class PortfolioServicoService {

	@Autowired
	private IPortfolioServicoRepository repServico;

	@Autowired
	private IRamoAtividadeRepository repRamo;

	/**
	 * Método para inicializar Lista de Serviços do Portfolio do Afiliado.
	 *
	 * @param idAfiliado {@link Long} com o {@code ID} do Afiliado
	 * @return {@code List<}{@link UserPortfolioServico}{@code >}
	 */
	public List<UserPortfolioServico> encontrarListaPortfolioServicos(final Long idAfiliado) {

		final List<UserPortfolioServico> lista = new ArrayList<UserPortfolioServico>();

		final Set<PortfolioServico> portServicos = this.repServico.findAllByAfiliadoIdAfiliado(idAfiliado);

		if (null != portServicos && !portServicos.isEmpty()) {

			portServicos.forEach(servico -> {
				lista.add(this.acrescentarServico(servico));
			});
		}

		return lista;
	}

	/**
	 * Método para cadastro do Serviço de Portfólio do Afiliado.
	 *
	 * @param afiliado {@link Afiliado} com informações do Afiliado
	 * @param userServico {@link UserPortfolioServico} com as informações do Serviço do Portfólio do Afiliado
	 * @return {@code RetornoCadastro} com o {@code ID} cadastrado com sucesso
	 */
	public RetornoCadastro cadastrarPortfolioServico(final Afiliado afiliado, final UserPortfolioServico userServico) {

		final PortfolioServico portServico = new PortfolioServico();
		portServico.setNomEmpresa(userServico.getNomeEmpresa());
		portServico.setDesEmpresa(userServico.getDescricao());
		portServico.setIdnFuncional(userServico.getIdentificacao());
		portServico.setUrlDivulgacao(userServico.getUrlEmpresa());
		portServico.setUrlVideo(userServico.getUrlVideo());

		if (null != userServico.getRamoAtividades() && !userServico.getRamoAtividades().isEmpty()) {

			portServico.setLstRamoAtividade(new HashSet<RamoAtividade>());

			userServico.getRamoAtividades().forEach(ramo -> {

				if (ramo.isSituacao()) {

					portServico.getLstRamoAtividade().add(this.verificarRamoAtividade(ramo));
				}
			});
		}

		final PortfolioServico cadastrado = this.repServico.save(portServico);

		final RetornoCadastro retorno = new RetornoCadastro();
		retorno.setId(cadastrado.getIdServico());

		return retorno;
	}

	/**
	 * Método para atualizar o Serviço de Portfólio do Afiliado.
	 *
	 * @param userEndereco {@link UserPortfolioServico} com as informações do Serviço do Portfólio do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se atualizado com sucesso
	 */
	public boolean atualizarPortfolioServico(final UserPortfolioServico userServico) {

		boolean icAtualizado = false;

		final PortfolioServico servico = this.repServico.findById(userServico.getId()).orElse(null);

		if (null != servico) {

			servico.setNomEmpresa(userServico.getNomeEmpresa());
			servico.setDesEmpresa(userServico.getDescricao());
			servico.setIdnFuncional(userServico.getIdentificacao());
			servico.setUrlDivulgacao(userServico.getUrlEmpresa());
			servico.setUrlVideo(userServico.getUrlVideo());

			if (null != servico.getLstRamoAtividade() && !servico.getLstRamoAtividade().isEmpty()) {

				if (null == servico.getLstRamoAtividade() || servico.getLstRamoAtividade().isEmpty()) {

					servico.setLstRamoAtividade(new HashSet<RamoAtividade>());
				}

				if (servico.getLstRamoAtividade().size() > 0) {

					final Iterator<RamoAtividade> ramos = servico.getLstRamoAtividade().iterator();

					final RamoAtividade ramoCadastrado = ramos.next();
					boolean encontrado = false;

					for (RamoAtividade ramoNovo : servico.getLstRamoAtividade()) {
						if (ramoCadastrado.getNomRamo().equals(ramoNovo.getNomRamo())) {

							encontrado = true;
							break;
						}
					}
					if (!encontrado) {

						ramos.remove();
					}

				}

				this.repServico.save(servico);
				icAtualizado = true;
			}
		}

		return icAtualizado;
	}

	public RamoAtividade verificarRamoAtividade(final UserRamoAtividade ramo) {

		RamoAtividade ramoAtividade = this.repRamo.findByNomRamo(ramo.getNome()).orElse(null);

		if (null == ramoAtividade) {

			ramoAtividade = new RamoAtividade();
			ramoAtividade.setNomRamo(ramo.getNome());
		}

		return ramoAtividade;
	}

	private UserPortfolioServico acrescentarServico(final PortfolioServico servico) {

		final UserPortfolioServico portServico = new UserPortfolioServico();
		portServico.setId(servico.getIdServico());
		portServico.setNomeEmpresa(servico.getNomEmpresa());
		portServico.setDescricao(servico.getDesEmpresa());
		portServico.setIdentificacao(servico.getIdnFuncional());
		portServico.setUrlEmpresa(servico.getUrlDivulgacao());
		portServico.setUrlVideo(servico.getUrlVideo());
		portServico.setRamoAtividades(new ArrayList<UserRamoAtividade>());

		if (null != servico.getLstRamoAtividade() && !servico.getLstRamoAtividade().isEmpty()) {

			servico.getLstRamoAtividade().forEach(ramo -> {

				portServico.getRamoAtividades().add(this.acrescentarRamo(ramo));
			});
		}

		return portServico;
	}

	private UserRamoAtividade acrescentarRamo(final RamoAtividade ramo) {

		final UserRamoAtividade ramoAtividade = new UserRamoAtividade();
		ramoAtividade.setId(ramo.getIdRamo());
		ramoAtividade.setNome(ramo.getNomRamo());
		ramoAtividade.setSituacao(true);

		return ramoAtividade;
	}

}
