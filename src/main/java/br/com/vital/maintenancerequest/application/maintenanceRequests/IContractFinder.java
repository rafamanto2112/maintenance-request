package br.com.vital.maintenancerequest.application.maintenanceRequests;

public interface IContractFinder {

	ContractDto findByNumber(String contractNumber);

}
