package br.com.vital.maintenancerequest.application.maintenanceRequests;

import br.com.vital.maintenancerequest.application.subsidiaries.ISubsidiaryRepository;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.MaintenanceRequest;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.RequestType;
import br.com.vital.maintenancerequest.domain.subsidiaries.Subsidiary;

public class MaintenanceRequester {

	private final ISubsidiaryRepository subsidiaryRepository;
	private final IMaintenanceRequestRepository maintenanceRequestRepository;

	public MaintenanceRequester(final ISubsidiaryRepository subsidiaryRepository, final IMaintenanceRequestRepository maintenanceRequestRepository) {
		this.subsidiaryRepository = subsidiaryRepository;
		this.maintenanceRequestRepository = maintenanceRequestRepository;
	}

	public void request(final MaintanenceRequestDto dto) {

		final Subsidiary subsidiary = subsidiaryRepository.getById(dto.getSubsidiaryId());

		final RequestType requestType = RequestType.parseByCode(dto.getRequestType());

		final MaintenanceRequest maintanenceRequest = new MaintenanceRequest(
				requester,
				approver,
				subsidiary,
				type,
				justification,
				contract,
				startDate,
				status)


	}

}
