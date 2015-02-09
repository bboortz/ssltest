/**
 * 
 */
package eu.boortz.ssltest.lib.exception;

/**
 * @author benni
 *
 */
public class ClientConnectException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1558764130841981055L;

	/**
	 * @param message
	 */
	public ClientConnectException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param message
	 * @param cause
	 */
	public ClientConnectException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ClientConnectException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
