package br.com.vital.maintenancerequest.domain.maintenancerequests.builder;

import java.time.LocalDate;

import br.com.vital.maintenancerequest.domain.maintenanceRequests.Author;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.Contract;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.MaintenanceRequest;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.RequestType;
import br.com.vital.maintenancerequest.domain.subsidiaries.buider.SubsidiaryBuider;

public class MaintenanceRequestBuilder {

	private MaintenanceRequest maintenanceRequest;

	public static MaintenanceRequestBuilder create() {
		final MaintenanceRequestBuilder builder = new MaintenanceRequestBuilder();
		
		builder.maintenanceRequest = new MaintenanceRequest(
				AuthorBuilder.create(), 
				SubsidiaryBuider.create(), 
				RequestType.PAINTING, 
				"justification", 
				ContractBuilder.create(), 
				LocalDate.now().plusYears(2));

		return builder;

	}

	public MaintenanceRequest build() {
		return maintenanceRequest;
	}

}
