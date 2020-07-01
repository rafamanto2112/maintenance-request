package br.com.vital.maintenancerequest.application.maintenanceRequests;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContractDto {
	
	private Long number;
	private String name;
	private String cnpj;
	private String manager;
	private LocalDate effetiveDate;

}
