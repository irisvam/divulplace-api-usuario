package br.com.divulplace.usuario.util.enums;

/**
 * {@code Enum} para opções utilizadas para o {@code SEXO}.
 *
 * @see #MASCULINO
 * @see #FEMININO
 * @see #INTERSEXO
 * @see #NDA
 */
public enum TipoSexo implements ICommonEnum {

	MASCULINO ("Masculino(a)"), 
	FEMININO ("Feminino(a)"), 
	INTERSEXO ("Intersexo"), 
	NDA ("Não declarado (a)");

	private String descricao;

	TipoSexo(final String descricao) {

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

		return TipoSexo.values()[ordinal()];
	}

}