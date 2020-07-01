package br.com.vital.maintenancerequest.application.maintenanceRequests;

import br.com.vital.maintenancerequest.domain.maintenanceRequests.Contract;

public class ContractFactory {

	public static Contract create(ContractDto contract) {
		return new Contract(contract.getNumber(), contract.getName(), contract.getCnpj(), contract.getManager(), contract.getEffetiveDate());
		
	}

}
