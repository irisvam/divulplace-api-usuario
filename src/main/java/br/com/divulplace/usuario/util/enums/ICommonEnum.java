package br.com.divulplace.usuario.util.enums;

/**
 * Interface Comum aos {@code ENUMs}
 */
public interface ICommonEnum {

	/**
	 * Workround pra o bug 6277781.
	 *
	 * @return instância
	 */
	Object readResolve();

}
