package br.com.vital.maintenancerequest.domain.maintenancerequests.builder;

import java.time.LocalDate;

import br.com.vital.maintenancerequest.domain.maintenanceRequests.Contract;

public class ContractBuilder {

	public static Contract create() {
		return new Contract(231243434L, "Voatzay Services", "24459873000125", "Thomas", LocalDate.now().plusYears(2));
	}

}
