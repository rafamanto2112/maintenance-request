package br.com.vital.maintenancerequest.application.maintenanceRequests;

import br.com.vital.maintenancerequest.application.IRepository;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.MaintenanceRequest;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.RequestType;

public interface IMaintenanceRequestRepository extends IRepository<MaintenanceRequest> {

	MaintenanceRequest getPendingByType(RequestType requestType, String subsidiaryId);

	void cancel(MaintenanceRequest maintenanceRequestPending);

}
