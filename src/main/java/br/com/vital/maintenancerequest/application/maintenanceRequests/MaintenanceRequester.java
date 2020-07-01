package br.com.vital.maintenancerequest.application.maintenanceRequests;

import java.util.Objects;

import br.com.vital.maintenancerequest.application.subsidiaries.ISubsidiaryRepository;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.Author;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.Contract;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.MaintenanceRequest;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.RequestType;
import br.com.vital.maintenancerequest.domain.subsidiaries.Subsidiary;

public class MaintenanceRequester {

	private final ISubsidiaryRepository subsidiaryRepository;
	private final IMaintenanceRequestRepository maintenanceRequestRepository;
	private final IContractFinder contractFinder;

	public MaintenanceRequester(final ISubsidiaryRepository subsidiaryRepository, final IMaintenanceRequestRepository maintenanceRequestRepository, IContractFinder contractFinder) {
		this.subsidiaryRepository = subsidiaryRepository;
		this.maintenanceRequestRepository = maintenanceRequestRepository;
		this.contractFinder = contractFinder;
	}

	public void request(final MaintanenceRequestDto dto) {

		final Subsidiary subsidiary = subsidiaryRepository.getById(dto.getSubsidiaryId());

		final RequestType requestType = RequestType.parseByCode(dto.getRequestType());
		
		Contract contract = ContractFactory.create(dto.getContract());
				
		Author requester = AuthorFactory.create(dto.getRequester());
		
		MaintenanceRequest maintenanceRequest = new MaintenanceRequest(requester, subsidiary, requestType, dto.getJustification(), contract, dto.getStartDate());
		
		MaintenanceRequest maintenanceRequestPending = maintenanceRequestRepository.getPendingByType(maintenanceRequest.getRequestType(), subsidiary.getId());
		
		if(Objects.nonNull(maintenanceRequestPending)) {
			maintenanceRequestRepository.cancel(maintenanceRequestPending);
		}
		
		maintenanceRequestRepository.insert(maintenanceRequest);

	}

}
