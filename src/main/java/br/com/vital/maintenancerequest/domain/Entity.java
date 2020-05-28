package br.com.vital.maintenancerequest.domain;

import br.com.vital.maintenancerequest.utils.GuidGenerator;
import lombok.Getter;

@Getter
public abstract class Entity {

	private final String id;

	public Entity() {
		id =  new GuidGenerator().generate();
	}

}
