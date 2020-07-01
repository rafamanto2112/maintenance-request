package br.com.vital.maintenancerequest.application.maintenanceRequests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vital.maintenancerequest.application.subsidiaries.ISubsidiaryRepository;
import br.com.vital.maintenancerequest.domain.DomainException;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.RequestType;
import br.com.vital.maintenancerequest.domain.maintenancerequests.builder.MaintenanceRequestBuilder;
import br.com.vital.maintenancerequest.domain.subsidiaries.buider.SubsidiaryBuider;

@RunWith(MockitoJUnitRunner.class)
public class MaintenanceRequesterTest {
	
	@InjectMocks
	private MaintenanceRequester maintanenceRequester;
	
	@Mock
	private IMaintenanceRequestRepository maintenanceRequestRepository;
	
	@Mock
	private ISubsidiaryRepository subsidiaryRepository;
	
	private MaintanenceRequestDto maintanenceRequestDto;
	
	@Before
	public void init() {
		maintanenceRequestDto = new MaintanenceRequestDto();
		maintanenceRequestDto.setRequestType(RequestType.CHANGE_NETWORK_POINT.getCode());
		
		RequesterDto requesterDto = new RequesterDto();
		requesterDto.setId(12345);
		requesterDto.setName("Tomas");
		
		maintanenceRequestDto.setRequester(requesterDto);
		
		ContractDto contractDto = new ContractDto();
		contractDto.setName("Heion Services Ltda");
		contractDto.setNumber(38892373287893L);
		contractDto.setManager("Aranfa");
		contractDto.setCnpj("45514766000122");
		contractDto.setEffetiveDate(LocalDate.now().plusYears(2l));
		
		maintanenceRequestDto.setContract(contractDto);
		maintanenceRequestDto.setStartDate(LocalDate.now().plusDays(2l));
		maintanenceRequestDto.setSubsidiaryId(2);
		maintanenceRequestDto.setJustification("network point missing");
	}
	
	@Test
	public void mustInsertMaintananceTest() {
		when(subsidiaryRepository.getById(Mockito.anyInt())).thenReturn(SubsidiaryBuider.create());
		
		maintanenceRequester.request(maintanenceRequestDto);
		
		verify(maintenanceRequestRepository, atLeast(1)).insert(Mockito.any());
		verify(maintenanceRequestRepository, never()).cancel(Mockito.any());
	}
	
	@Test
	public void mustCancelLastMaintananceBeforeInsertTest() {
		when(subsidiaryRepository.getById(Mockito.anyInt())).thenReturn(SubsidiaryBuider.create());
		when(maintenanceRequestRepository.getPendingByType(Mockito.any(), Mockito.any()))
			.thenReturn(MaintenanceRequestBuilder.create().build());
		
		maintanenceRequester.request(maintanenceRequestDto);
		
		verify(maintenanceRequestRepository, atLeast(1)).insert(Mockito.any());
		verify(maintenanceRequestRepository, atLeast(1)).cancel(Mockito.any());
	}
	
	@Test
	public void subsidiaryNotFoundTest() {
		when(subsidiaryRepository.getById(Mockito.anyInt())).thenReturn(null);
		
		try {
			maintanenceRequester.request(maintanenceRequestDto);
			fail();
		} catch(DomainException e) {
			assertEquals("Subsidiary must be informed", e.getMessage());
		}
		
		

		
	}

}
