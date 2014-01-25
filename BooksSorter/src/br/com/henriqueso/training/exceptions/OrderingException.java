/**
 *
 */
package br.com.henriqueso.training.exceptions;



/**
 * @author henrique
 * @email henrique@henriqueso.com.br
 * @since 24/01/2014
 *
 */
public class OrderingException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	/**
	 * @param message
	 */
	public OrderingException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OrderingException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
