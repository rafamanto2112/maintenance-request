package br.com.vital.maintenancerequest.domain.subsidiaries.buider;

import br.com.vital.maintenancerequest.domain.subsidiaries.Subsidiary;

public class SubsidiaryBuider {

	public static Subsidiary create() {
		return new Subsidiary("New York");
	}

}
