package br.com.divulplace.usuario.util.enums;

/**
 * {@code Enum} para opções utilizadas para mensagens de resposta.
 *
 * @see #MESSAGE
 */
public enum MessageProperties {

	MESSAGE("message");

	private final String descricao;

	/**
	 * Construtor padrão para este {@code Enum}.
	 *
	 * @param descricao um {@code String} que represente a descrição
	 */
	MessageProperties(final String descricao) {

		this.descricao = descricao;
	}

	public String getDescricao() {

		return descricao;
	}

	/**
	 * Método para ordenar os valores deste {@code Enum}.
	 *
	 * @return {@link MessageProperties}{@code []}
	 */
	public Object readResolve() {

		return MessageProperties.values()[ordinal()];
	}

}
