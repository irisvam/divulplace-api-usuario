package br.com.divulplace.usuario.ws.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.entity.Contato;
import br.com.divulplace.usuario.entity.Endereco;
import br.com.divulplace.usuario.entity.PortfolioEmpresa;
import br.com.divulplace.usuario.entity.PortfolioEmpresaRamoAtividadeId;
import br.com.divulplace.usuario.entity.RamoAtividade;
import br.com.divulplace.usuario.model.EmpresaContato;
import br.com.divulplace.usuario.model.EmpresaEndereco;
import br.com.divulplace.usuario.model.RetornoCadastro;
import br.com.divulplace.usuario.model.UserPortfolioEmpresa;
import br.com.divulplace.usuario.model.UserRamoAtividade;
import br.com.divulplace.usuario.ws.repositories.IPortfolioEmpresaRamoAtividadeRepository;
import br.com.divulplace.usuario.ws.repositories.IPortfolioEmpresaRepository;
import br.com.divulplace.usuario.ws.repositories.IRamoAtividadeRepository;

/**
 * Classe {@code Service} para formatação de dados de Empresas do Portfólio.
 */
@Service
public class PortfolioEmpresaService {

	@Autowired
	private IPortfolioEmpresaRepository repEmpresa;

	@Autowired
	private IRamoAtividadeRepository repRamo;

	@Autowired
	private IPortfolioEmpresaRamoAtividadeRepository repEmpresaRamo;

	/**
	 * Método para inicializar Lista de Empresas do Portfolio do Afiliado.
	 *
	 * @param idAfiliado {@link Long} com o {@code ID} do Afiliado
	 * @return {@code List<}{@link UserPortfolioEmpresa}{@code >}
	 */
	public List<UserPortfolioEmpresa> encontrarListaPortfolioEmpresas(final Long idAfiliado) {

		final List<UserPortfolioEmpresa> lista = new ArrayList<UserPortfolioEmpresa>();

		final Set<PortfolioEmpresa> portEmpresa = this.repEmpresa.findAllByAfiliadoIdAfiliado(idAfiliado);

		if (null != portEmpresa && !portEmpresa.isEmpty()) {

			portEmpresa.forEach(empresa -> {
				lista.add(this.acrescentarEmpresa(empresa));
			});
		}

		return lista;
	}

	/**
	 * Método para cadastro de Empresa de Portfólio do Afiliado.
	 *
	 * @param afiliado {@link Afiliado} com informações do Afiliado
	 * @param userEmpresa {@link UserPortfolioEmpresa} com as informações de Empresa do Portfólio do Afiliado
	 * @return {@code RetornoCadastro} com o {@code ID} cadastrado com sucesso
	 */
	public RetornoCadastro cadastrarPortfolioEmpresa(final Afiliado afiliado, final UserPortfolioEmpresa userEmpresa) {

		final PortfolioEmpresa portEmpresa = new PortfolioEmpresa();
		portEmpresa.setNomEmpresa(userEmpresa.getNomeEmpresa());
		portEmpresa.setDesEmpresa(userEmpresa.getDescricao());
		portEmpresa.setDocCnpj(userEmpresa.getCnpj());
		portEmpresa.setUrlDivulgacao(userEmpresa.getUrlEmpresa());
		portEmpresa.setUrlVideo(userEmpresa.getUrlVideo());
		portEmpresa.setAfiliado(afiliado);

		if (null != userEmpresa.getRamoAtividades() && !userEmpresa.getRamoAtividades().isEmpty()) {

			portEmpresa.setLstRamoAtividade(new HashSet<RamoAtividade>());

			userEmpresa.getRamoAtividades().forEach(ramo -> {

				if (ramo.isSituacao()) {

					portEmpresa.getLstRamoAtividade().add(this.verificarRamoAtividade(ramo));
				}
			});
		}

		if (null != userEmpresa.getContato()) {

			portEmpresa.setContato(new Contato());
			this.atualizarContato(portEmpresa.getContato(), userEmpresa.getContato());
		}

		if (null != userEmpresa.getEndereco()) {

			portEmpresa.setEndereco(new Endereco());
			this.atualizarEndereco(portEmpresa.getEndereco(), userEmpresa.getEndereco());
		}

		final PortfolioEmpresa cadastrado = this.repEmpresa.save(portEmpresa);

		final RetornoCadastro retorno = new RetornoCadastro();
		retorno.setId(cadastrado.getIdEmpresa());

		return retorno;
	}

	/**
	 * Método para encontrar dados da Empresa desejada.
	 *
	 * @param idEmpresa {@link Long} com o {@code ID} da Empresa
	 * @return {@link UserPortfolioEmpresa} com as informações da Empresa
	 */
	public UserPortfolioEmpresa encontrarEmpresa(final Long idEmpresa) {

		UserPortfolioEmpresa userEmpresa = null;

		final PortfolioEmpresa empresa = this.repEmpresa.findById(idEmpresa).orElse(null);

		if (null != empresa) {

			userEmpresa = acrescentarEmpresa(empresa);
		}

		return userEmpresa;
	}

	/**
	 * Método para atualizar a Empresa de Portfólio do Afiliado.
	 *
	 * @param idEmpresa {@code ID} da Empresa a ser atualizada
	 * @param userEmpresa {@link UserPortfolioEmpresa} com as informações da Empresa do Portfólio do Afiliado
	 * @return {@code boolean} com {@code TRUE|FALSE} se atualizado com sucesso
	 */
	public boolean atualizarPortfolioEmpresa(final Long idEmpresa, final UserPortfolioEmpresa userEmpresa) {

		boolean icAtualizado = false;

		final PortfolioEmpresa empresa = this.repEmpresa.findById(idEmpresa).orElse(null);

		if (null != empresa) {

			empresa.setNomEmpresa(userEmpresa.getNomeEmpresa());
			empresa.setDesEmpresa(userEmpresa.getDescricao());
			empresa.setDocCnpj(userEmpresa.getCnpj());
			empresa.setUrlDivulgacao(userEmpresa.getUrlEmpresa());
			empresa.setUrlVideo(userEmpresa.getUrlVideo());

			if (null != userEmpresa.getRamoAtividades() && !userEmpresa.getRamoAtividades().isEmpty()) {

				if (null == empresa.getLstRamoAtividade()) {

					empresa.setLstRamoAtividade(new HashSet<RamoAtividade>());
				}

				if (empresa.getLstRamoAtividade().size() > 0) {

					this.atualizarRamosInativo(empresa.getIdEmpresa(), empresa.getLstRamoAtividade(), userEmpresa.getRamoAtividades());
				}

				this.atualizarRamosAtivo(empresa.getLstRamoAtividade(), userEmpresa.getRamoAtividades());
			}

			if (null != userEmpresa.getContato()) {

				if (null == empresa.getContato()) {

					empresa.setContato(new Contato());
				}

				this.atualizarContato(empresa.getContato(), userEmpresa.getContato());
			}

			if (null != userEmpresa.getEndereco()) {

				if (null == empresa.getEndereco()) {

					empresa.setEndereco(new Endereco());
				}

				this.atualizarEndereco(empresa.getEndereco(), userEmpresa.getEndereco());
			}

			this.repEmpresa.save(empresa);
			icAtualizado = true;
		}

		return icAtualizado;
	}

	private void atualizarContato(final Contato contato, final EmpresaContato empContato) {

		contato.setTelFixo(empContato.getTelefone());
		contato.setCelPrimeiro(empContato.getCelular01());
		contato.setIcCelPriWhatsapp(empContato.isCelular01WS());
		contato.setCelSegundo(empContato.getCelular02());
		contato.setIcCelPriWhatsapp(empContato.isCelular02WS());
		contato.setDesSkype(empContato.getEmail());
	}

	private void atualizarEndereco(final Endereco endereco, final EmpresaEndereco empEndereco) {

		endereco.setCodPais(empEndereco.getPais());
		endereco.setCodCEP(empEndereco.getCep());
		endereco.setDesEndereco(empEndereco.getLogradouro());
		endereco.setNumEndereco(null == empEndereco.getNumero() ? 0 : empEndereco.getNumero().shortValue());
		endereco.setDesComplemento(empEndereco.getComplemento());
		endereco.setDesBairro(empEndereco.getBairro());
		endereco.setUfEstado(empEndereco.getEstado());
		endereco.setCodCidade(null == empEndereco.getCidadeId() ? 0 : empEndereco.getCidadeId());
		endereco.setDesCidade(empEndereco.getCidade());
	}

	/**
	 * Método para deletar Empresa.
	 *
	 * @param idEmpresa {@code ID} da Empresa
	 * @return {@code boolean} com {@code TRUE|FALSE} se deletado com sucesso
	 */
	public boolean deletarEmpresa(final Long idEmpresa) {

		boolean retorno = false;

		final PortfolioEmpresa empresa = this.repEmpresa.findById(idEmpresa).orElse(null);

		if (null != empresa) {

			this.repEmpresa.deleteById(idEmpresa);
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
	 * @param idEmpresa {@code ID} da Empresa caso necessário
	 * @param lstRamoAtividade {@code Set<}{@link RamoAtividade}{@code >} com os Ramos cadastrados
	 * @param ramoAtividades {@code List<}{@link UserRamoAtividade}{@code >} com os Ramos Novos
	 */
	private void atualizarRamosInativo(final Long idEmpresa, final Set<RamoAtividade> lstRamoAtividade,
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

				final PortfolioEmpresaRamoAtividadeId idEmpresaRamo = new PortfolioEmpresaRamoAtividadeId(idEmpresa, ramoCadastrado.getIdRamo());

				repEmpresaRamo.deleteById(idEmpresaRamo);

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
	 * Método para preparar a Empresa para ser enviado externamente.
	 *
	 * @param servico {@link PortfolioEmpresa} com as informações da empresa cadastrado
	 * @return {@link UserPortfolioEmpresa} com os dados da Empresa a ser informado
	 */
	private UserPortfolioEmpresa acrescentarEmpresa(final PortfolioEmpresa empresa) {

		final UserPortfolioEmpresa portEmpresa = new UserPortfolioEmpresa();
		portEmpresa.setId(empresa.getIdEmpresa());
		portEmpresa.setNomeEmpresa(empresa.getNomEmpresa());
		portEmpresa.setDescricao(empresa.getDesEmpresa());
		portEmpresa.setCnpj(empresa.getDocCnpj());
		portEmpresa.setUrlEmpresa(empresa.getUrlDivulgacao());
		portEmpresa.setUrlVideo(empresa.getUrlVideo());
		portEmpresa.setRamoAtividades(new ArrayList<UserRamoAtividade>());

		if (null != empresa.getLstRamoAtividade() && !empresa.getLstRamoAtividade().isEmpty()) {

			empresa.getLstRamoAtividade().forEach(ramo -> {

				portEmpresa.getRamoAtividades().add(this.acrescentarRamo(ramo));
			});
		}

		if (null != empresa.getContato()) {

			portEmpresa.setContato(this.acrescentarContato(empresa.getContato()));
		}

		if (null != empresa.getEndereco()) {

			portEmpresa.setEndereco(this.acrescentarEndereco(empresa.getEndereco()));
		}

		return portEmpresa;
	}

	private EmpresaContato acrescentarContato(final Contato contato) {

		final EmpresaContato empContato = new EmpresaContato();

		empContato.setId(contato.getIdContato());
		empContato.setTelefone(contato.getTelFixo());
		empContato.setCelular01(contato.getCelPrimeiro());
		empContato.setCelular01WS(contato.isIcCelPriWhatsapp());
		empContato.setCelular02(contato.getCelSegundo());
		empContato.setCelular02WS(contato.isIcCelSegWhatsapp());
		empContato.setEmail(contato.getDesSkype());

		return empContato;
	}

	private EmpresaEndereco acrescentarEndereco(final Endereco endereco) {

		final EmpresaEndereco empEndereco = new EmpresaEndereco();

		empEndereco.setId(endereco.getIdEndereco());
		empEndereco.setPais(endereco.getCodPais());
		empEndereco.setCep(endereco.getCodCEP());
		empEndereco.setLogradouro(endereco.getDesEndereco());
		empEndereco.setNumero(endereco.getNumEndereco().intValue());
		empEndereco.setComplemento(endereco.getDesComplemento());
		empEndereco.setBairro(endereco.getDesBairro());
		empEndereco.setEstado(endereco.getUfEstado());
		empEndereco.setCidadeId(endereco.getCodCidade());
		empEndereco.setCidade(endereco.getDesCidade());

		return empEndereco;
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

	public IPortfolioEmpresaRepository getRepEmpresa() {

		return repEmpresa;
	}

}
