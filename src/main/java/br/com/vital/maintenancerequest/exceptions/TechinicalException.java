package br.com.vital.maintenancerequest.exceptions;

public class TechinicalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TechinicalException(final String message, final Throwable throwable) {
		super(message, throwable);
	}


}
