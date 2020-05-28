package br.com.vital.maintenancerequest.domain.maintenanceRequests;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum RequestType {

	GRASS_TRIMMING(1),
	PAINTING(2),
	LAMP_CHANGE(3),
	CHANGE_NETWORK_POINT(4),
	ADD_NETWORK_POINT(5),
	GENERATOR(6);

	private Integer code;

	public static RequestType parseByCode(final Integer id) {
		return Arrays.stream(RequestType.values()).filter(r -> r.getCode().equals(id)).findFirst().orElse(null);
	}



}
