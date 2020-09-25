package br.com.divulplace.usuario.util;

import java.io.Serializable;

import br.com.divulplace.usuario.util.enums.ICommonEnum;

/**
 * Singleton para encontrar valores do tipo {@link ICommonEnum}.
 */
public class EnumCommonUtil implements Serializable {

	private static final long serialVersionUID = 1429595911140980956L;

	private static final EnumCommonUtil INSTANCE = new EnumCommonUtil();

	/**
	 * Construtor privado para a classe {@code Singleton}.
	 */
	private EnumCommonUtil() {

	}

	/**
	 * Método para encontrar {@code ENUM} pelo nome dele.
	 *
	 * @param <E> {@link} ICommonEnum
	 * @param nome {@link String} como nome do {@code ENUM} a encontrar
	 * @param enumValues {@code E[]} array com os valores dos {@code ENUMs} a procurar
	 * @return {@code E} com o {@code ENUM} encontrado ou {@code NULL} quando não encontrado
	 */
	public <E extends ICommonEnum> E encontrarEnum(final String nome, final E[] enumValues) {

		E retorno = null;

		for (final E tipoEnum : enumValues) {

			if (nome.equals(tipoEnum.toString())) {

				retorno = tipoEnum;
				break;
			}
		}

		return retorno;
	}

	/**
	 * Recupera a única instância desta classe.
	 * 
	 * @return {@link EnumCommonUtil}
	 */
	public static EnumCommonUtil getInstance() {

		return INSTANCE;
	}

}
