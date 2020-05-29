package br.com.vital.maintenancerequest.domain.maintenancerequests.builder;

import br.com.vital.maintenancerequest.domain.maintenanceRequests.Author;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.Contract;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.MaintenanceRequest;

public class MaintenanceRequestBuilder {

	private MaintenanceRequest maintenanceRequest;

	public static MaintenanceRequestBuilder create() {
		final MaintenanceRequestBuilder builder = new MaintenanceRequestBuilder();

		final Author requester = AuthorBuilder.create();
		final Author approver = AuthorBuilder.create();
		final Contract contract = ContractBuilder.create();

		return builder;

	}

	public MaintenanceRequest build() {
		return maintenanceRequest;
	}

}
