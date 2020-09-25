package br.com.divulplace.usuario.util.enums;

/**
 * {@code Enum} para opções utilizadas para o {@code ESTADO CIVIL}.
 *
 * @see #SOLTEIRO
 * @see #CASADO
 * @see #DIVORCIADO
 * @see #VIUVO
 * @see #SEPARADO
 */
public enum TipoEstadoCivil implements ICommonEnum {

	SOLTEIRO ("Solteiro(a)"), 
	CASADO ("Casado(a)"), 
	DIVORCIADO ("Divorciado(a)"), 
	VIUVO ("Viúvo(a)"), 
	SEPARADO ("Separado(a)"), 
	OUTRO ("Outro");

	private String descricao;

	TipoEstadoCivil(final String descricao) {

		this.descricao = descricao;
	}

	public String getDescricao() {

		return descricao;
	}

	public void setDescricao(final String descricao) {

		this.descricao = descricao;
	}

	@Override
	public Object readResolve() {

		return TipoEstadoCivil.values()[ordinal()];
	}

}