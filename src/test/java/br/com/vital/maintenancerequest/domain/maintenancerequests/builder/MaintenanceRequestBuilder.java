package br.com.vital.maintenancerequest.domain.maintenancerequests.builder;

import java.time.LocalDate;

import br.com.vital.maintenancerequest.domain.maintenanceRequests.Author;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.Contract;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.MaintenanceRequest;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.RequestType;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.StatusType;
import br.com.vital.maintenancerequest.domain.subsidiaries.buider.SubsidiaryBuider;

public class MaintenanceRequestBuilder {

	private MaintenanceRequest maintenanceRequest;

	public static MaintenanceRequestBuilder create() {
		final MaintenanceRequestBuilder builder = new MaintenanceRequestBuilder();

		final Author requester = AuthorBuilder.create();
		final Author approver = AuthorBuilder.create();
		final Contract contract = ContractBuilder.create();

		builder.maintenanceRequest = new MaintenanceRequest(requester,
				approver,
				SubsidiaryBuider.create(),
				RequestType.LAMP_CHANGE,
				"burnt-out lamps",
				contract,
				LocalDate.now().plusMonths(1),
				StatusType.PENDING);

		return builder;

	}

	public MaintenanceRequest build() {
		return maintenanceRequest;
	}

}
