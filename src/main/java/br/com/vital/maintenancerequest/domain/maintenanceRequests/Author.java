package br.com.vital.maintenancerequest.domain.maintenanceRequests;

import java.util.Objects;

import br.com.vital.maintenancerequest.domain.DomainException;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Author {

	private Integer identify;
	private String name;

	public Author(final Integer identify, final String name) {

		if(Objects.isNull(identify) || identify == 0) {
			throw new DomainException("D00201", "The identify must be informed");
		}

		if(Objects.isNull(name) || name.trim().isEmpty()) {
			throw new DomainException("D00202", "The name must be informed");

		}


		this.identify = identify;
		this.name = name;

	}

}
