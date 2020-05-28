package br.com.vital.maintenancerequest.domain;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String code;

	public DomainException(final String code, final String message) {
		super(message);
		this.code = code;
	}


}
