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
import br.com.divulplace.usuario.entity.PortfolioServicoRamoAtividadeId;
import br.com.divulplace.usuario.entity.RamoAtividade;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserPortfolioServico;
import br.com.divulplace.usuario.model.UserRamoAtividade;
import br.com.divulplace.usuario.ws.repositories.IPortfolioServicoRamoAtividadeRepository;
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

	@Autowired
	private IPortfolioServicoRamoAtividadeRepository repServicoRamo;

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
		portServico.setAfiliado(afiliado);

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
	 * @param idService {@code ID} do Serviço a ser atualizado
	 * @param userEndereco {@link UserPortfolioServico} com as informações do Serviço do Portfólio do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se atualizado com sucesso
	 */
	public boolean atualizarPortfolioServico(final Long idService, final UserPortfolioServico userServico) {

		boolean icAtualizado = false;

		final PortfolioServico servico = this.repServico.findById(idService).orElse(null);

		if (null != servico) {

			servico.setNomEmpresa(userServico.getNomeEmpresa());
			servico.setDesEmpresa(userServico.getDescricao());
			servico.setIdnFuncional(userServico.getIdentificacao());
			servico.setUrlDivulgacao(userServico.getUrlEmpresa());
			servico.setUrlVideo(userServico.getUrlVideo());

			if (null != userServico.getRamoAtividades() && !userServico.getRamoAtividades().isEmpty()) {

				if (null == servico.getLstRamoAtividade()) {

					servico.setLstRamoAtividade(new HashSet<RamoAtividade>());
				}

				if (servico.getLstRamoAtividade().size() > 0) {

					this.atualizarRamosInativo(servico.getIdServico(), servico.getLstRamoAtividade(), userServico.getRamoAtividades());
				}

				this.atualizarRamosAtivo(servico.getLstRamoAtividade(), userServico.getRamoAtividades());
			}

			this.repServico.save(servico);
			icAtualizado = true;
		}

		return icAtualizado;
	}

	/**
	 * Método para deletar Serviço.
	 *
	 * @param idService {@code ID} do Serviço
	 * @return {@code boolean} com {@code TRUE|FALSE} se deletado com sucesso
	 */
	public boolean deletarServico(final Long idService) {

		boolean retorno = false;

		final PortfolioServico servico = this.repServico.findById(idService).orElse(null);

		if (null != servico) {

			this.repServico.deleteById(idService);
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Método para acrescentrar os Ramos de Atividades novos.
	 *
	 * @param lstRamoAtividade {@code Set<}{@link RamoAtividade}{@code >} com os Ramos cadastrados
	 * @param ramoAtividades {@code List<}{@link UserRamoAtividade}{@code >} com os Ramos Novos
	 */
	private void atualizarRamosAtivo(final Set<RamoAtividade> lstRamoAtividade, final List<UserRamoAtividade> ramoAtividades) {

		for (final UserRamoAtividade ramoNovo : ramoAtividades) {

			boolean acrescentar = false;

			if (ramoNovo.isSituacao()) {

				acrescentar = true;
				for (final RamoAtividade ramoCadastrado : lstRamoAtividade) {

					if (ramoCadastrado.getNomRamo().equals(ramoNovo.getNome())) {

						acrescentar = false;
						break;
					}
				}
			}

			if (acrescentar) {

				lstRamoAtividade.add(this.verificarRamoAtividade(ramoNovo));
			}
		}
	}

	/**
	 * Método para retirar da lista os Ramos de Atividades que estarão inativos.
	 *
	 * @param idServico {@code ID} do Serviço caso necessário
	 * @param lstRamoAtividade {@code Set<}{@link RamoAtividade}{@code >} com os Ramos cadastrados
	 * @param ramoAtividades {@code List<}{@link UserRamoAtividade}{@code >} com os Ramos Novos
	 */
	private void atualizarRamosInativo(final Long idServico, final Set<RamoAtividade> lstRamoAtividade,
			final List<UserRamoAtividade> ramoAtividades) {

		final Iterator<RamoAtividade> ramos = lstRamoAtividade.iterator();

		while (ramos.hasNext()) {

			final RamoAtividade ramoCadastrado = ramos.next();
			boolean mantem = false;

			for (final UserRamoAtividade ramoNovo : ramoAtividades) {

				if (ramoNovo.isSituacao() && ramoNovo.getNome().equals(ramoCadastrado.getNomRamo())) {

					mantem = true;
					break;
				}
			}

			if (!mantem) {

				final PortfolioServicoRamoAtividadeId idServicoRamo = new PortfolioServicoRamoAtividadeId(idServico, ramoCadastrado.getIdRamo());

				repServicoRamo.deleteById(idServicoRamo);

				ramos.remove();
			}
		}
	}

	/**
	 * Método para verificar se Ramo de Atividade já está cadastrado e devolvê-lo, caso não cadastra-lo como uma
	 * entidade nova.
	 * 
	 * @param ramo {@link UserRamoAtividade} com o Ramo de Atividade a ser verificado
	 * @return {@link RamoAtividade} para associação da lista
	 */
	private RamoAtividade verificarRamoAtividade(final UserRamoAtividade ramo) {

		RamoAtividade ramoAtividade = this.repRamo.findByNomRamo(ramo.getNome()).orElse(null);

		if (null == ramoAtividade) {

			ramoAtividade = new RamoAtividade();
			ramoAtividade.setNomRamo(ramo.getNome());

			ramoAtividade = this.repRamo.save(ramoAtividade);
		}

		return ramoAtividade;
	}

	/**
	 * Método para preparar o Serviço para ser enviado externamente.
	 *
	 * @param servico {@link PortfolioServico} com as informações do serviço cadastrado
	 * @return {@link UserPortfolioServico} com os dados do Serviço a ser informado
	 */
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

	/**
	 * Método para preparar as informações dos Ramos de Atividades a ser disponibilizados.
	 * 
	 * @param ramo {@link RamoAtividade} com as informações do Ramo de Atividade Cadastrado
	 * @return {@link UserRamoAtividade} com os dados do Ramo de Atividade a ser informado
	 */
	private UserRamoAtividade acrescentarRamo(final RamoAtividade ramo) {

		final UserRamoAtividade ramoAtividade = new UserRamoAtividade();
		ramoAtividade.setNome(ramo.getNomRamo());
		ramoAtividade.setSituacao(true);

		return ramoAtividade;
	}

	public IPortfolioServicoRepository getRepServico() {

		return repServico;
	}

}
