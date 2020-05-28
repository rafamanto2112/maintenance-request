package br.com.vital.maintenancerequest.domain.subsidiaries;

import java.util.Objects;

import br.com.vital.maintenancerequest.domain.DomainException;
import br.com.vital.maintenancerequest.domain.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Subsidiary extends Entity {

	private String name;

	public Subsidiary(final String name) {
		if(Objects.isNull(name) || name.isEmpty()) {
			throw new DomainException("D00401", "Name invalid");
		}

		this.name = name;
	}

}
