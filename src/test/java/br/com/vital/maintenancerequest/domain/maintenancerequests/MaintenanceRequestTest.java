package br.com.vital.maintenancerequest.domain.maintenancerequests;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vital.maintenancerequest.domain.DomainException;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.MaintenanceRequest;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.RequestType;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.StatusType;
import br.com.vital.maintenancerequest.domain.maintenancerequests.builder.AuthorBuilder;
import br.com.vital.maintenancerequest.domain.maintenancerequests.builder.ContractBuilder;
import br.com.vital.maintenancerequest.domain.subsidiaries.buider.SubsidiaryBuider;

@RunWith(MockitoJUnitRunner.class)
public class MaintenanceRequestTest {

	@Test
	public void criatingWithSucessTest() {


		final MaintenanceRequest request = new MaintenanceRequest(AuthorBuilder.create(), SubsidiaryBuider.create(),
				RequestType.ADD_NETWORK_POINT, "falting network point", ContractBuilder.create(), LocalDate.now());

		Assert.assertNotNull(request.getId());
		Assert.assertNotNull(request.getRequester());
		Assert.assertNotNull(request.getSubsidiary());
		Assert.assertNotNull(request.getRequestType());
		Assert.assertEquals(RequestType.ADD_NETWORK_POINT, request.getRequestType());
		Assert.assertNotNull(request.getJustification());
		Assert.assertNotNull(request.getContract());
		Assert.assertEquals(LocalDate.now(), request.getStartDate());
		Assert.assertEquals(StatusType.PENDING, request.getStatus());
	}


	@Test
	public void creatingWithoutContractTest() {

		try {
			new MaintenanceRequest(AuthorBuilder.create(), SubsidiaryBuider.create(),
					RequestType.GENERATOR, "constant power outage", null, LocalDate.now());

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00108", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}


	@Test
	public void creatingWihoutRequesterTest() {
		try {
			new MaintenanceRequest(null, SubsidiaryBuider.create(),
					RequestType.PAINTING, "moldy walls", ContractBuilder.create(), LocalDate.now());

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00109", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutTypeTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), SubsidiaryBuider.create(), null, "any maintenance",
					ContractBuilder.create(), LocalDate.now());

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00101", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutStartDateTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), SubsidiaryBuider.create(),
					RequestType.LAMP_CHANGE, "burnt-out lamps", ContractBuilder.create(), null);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00102", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}


	@Test
	public void creatingWithLastStartDateTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), SubsidiaryBuider.create(),
					RequestType.LAMP_CHANGE, "burnt-out lamps", ContractBuilder.create(), LocalDate.now().minusDays(2));

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00103", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}


	@Test
	public void creatingWithoutSubsidiaryTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), null,
					RequestType.PAINTING, "moldy walls", ContractBuilder.create(), LocalDate.now());

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00106", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutJustificationTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), SubsidiaryBuider.create(),
					RequestType.PAINTING, "", ContractBuilder.create(), LocalDate.now());

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00107", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

}
