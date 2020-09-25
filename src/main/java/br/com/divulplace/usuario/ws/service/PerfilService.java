package br.com.divulplace.usuario.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.divulplace.usuario.entity.Afiliado;
import br.com.divulplace.usuario.model.UserPerfil;
import br.com.divulplace.usuario.util.EnumCommonUtil;
import br.com.divulplace.usuario.util.enums.TipoEstadoCivil;
import br.com.divulplace.usuario.util.enums.TipoSexo;
import br.com.divulplace.usuario.ws.repositories.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository repPerfil;

	public UserPerfil encontrarPerfil(final Long idUsuario) {

		UserPerfil userPerfil = null;

		final Afiliado afiliado = this.repPerfil.findByUsuarioId(idUsuario).orElse(null);

		if (null != afiliado) {

			userPerfil = new UserPerfil();

			userPerfil.setId(idUsuario);
			userPerfil.setCodigo("2018ACDAKL8765");
			userPerfil.setTratamento(afiliado.getDesTratamento());
			userPerfil.setImg(afiliado.getUrlImagem());
			userPerfil.setNome(afiliado.getDesNome());
			userPerfil.setApelido(afiliado.getDesApelido());
			userPerfil.setCpf(afiliado.getDocCpf());
			userPerfil.setDataNascimento(afiliado.getDtaNascimento());
			userPerfil.setEstadoCivil((null != afiliado.getTpoEstadoCivil() ? afiliado.getTpoEstadoCivil().name() : ""));
			userPerfil.setSexo((null != afiliado.getTpoSexo() ? afiliado.getTpoSexo().name() : ""));
			userPerfil.setSobre(afiliado.getDesSobre());
			userPerfil.setEmail(afiliado.getDesEmail());
			userPerfil.setLink(afiliado.getUrlLink());
		}

		return userPerfil;
	}

	public boolean atualizarPerfil(final Long idUsuario, final UserPerfil userPerfil) {

		boolean icAtualizado = false;

		final Afiliado afiliado = this.repPerfil.findByUsuarioId(idUsuario).orElse(null);

		if (null != afiliado) {

			afiliado.setDesTratamento(userPerfil.getTratamento());
			afiliado.setDesNome(userPerfil.getNome());
			afiliado.setDesApelido(userPerfil.getApelido());
			afiliado.setDesEmail(userPerfil.getEmail());
			afiliado.setDtaNascimento(userPerfil.getDataNascimento());
			afiliado.setUrlImagem(userPerfil.getImg());
			afiliado.setUrlLink(userPerfil.getLink());
			afiliado.setDocCpf(userPerfil.getCpf());
			afiliado.setTpoEstadoCivil(EnumCommonUtil.getInstance().encontrarEnum(userPerfil.getEstadoCivil(), TipoEstadoCivil.values()));
			afiliado.setTpoSexo(EnumCommonUtil.getInstance().encontrarEnum(userPerfil.getSexo(), TipoSexo.values()));
			afiliado.setDesSobre(userPerfil.getSobre());
			
			this.repPerfil.save(afiliado);
			icAtualizado = true;
		}

		return icAtualizado;
	}

}
