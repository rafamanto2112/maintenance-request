package br.com.vital.maintenancerequest.application.maintenanceRequests;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MaintanenceRequestDto {

	private Integer subsidiaryId;
	private Integer requestType;
	private ContractDto contract;
	private RequesterDto requester;
	private String justification;
	private LocalDate startDate;

}
