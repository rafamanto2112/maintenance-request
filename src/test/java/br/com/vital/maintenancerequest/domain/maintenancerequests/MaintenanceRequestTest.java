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
import br.com.vital.maintenancerequest.domain.maintenancerequests.builder.MaintenanceRequestBuilder;
import br.com.vital.maintenancerequest.domain.subsidiaries.buider.SubsidiaryBuider;

@RunWith(MockitoJUnitRunner.class)
public class MaintenanceRequestTest {

	@Test
	public void criatingWithSucessTest() {


		final MaintenanceRequest request = MaintenanceRequestBuilder.create().build();

		Assert.assertNotNull(request.getId());
		Assert.assertNotNull(request.getRequester());
		Assert.assertNotNull(request.getApprover());
		Assert.assertNotNull(request.getSubsidiary());
		Assert.assertNotNull(request.getRequestType());
		Assert.assertNotNull(request.getJustification());
		Assert.assertNotNull(request.getStartDate());
		Assert.assertNotNull(request.getStatus());
		Assert.assertNotNull(request.getContract());
	}

	@Test
	public void creatingWithoutContractTest() {

		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(), SubsidiaryBuider.create(), RequestType.GENERATOR,
					"constant power outage", null, LocalDate.now().plusDays(1), StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00108", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWihoutRequesterTest() {
		try {
			new MaintenanceRequest(null, AuthorBuilder.create(), SubsidiaryBuider.create(), RequestType.PAINTING,
					"moldy walls", ContractBuilder.create(), LocalDate.now().plusDays(1), StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00109", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutApproverTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), null, SubsidiaryBuider.create(), RequestType.CHANGE_NETWORK_POINT,
					"missing network points", ContractBuilder.create(), LocalDate.now().plusDays(1), StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00110", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutTypeTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(), SubsidiaryBuider.create(), null,
					"any maintenance", ContractBuilder.create(), LocalDate.now().plusDays(1), StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00101", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutStartDateTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(), SubsidiaryBuider.create(), RequestType.LAMP_CHANGE,
					"burnt-out lamps", ContractBuilder.create(), null, StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00102", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithLastStartDateTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(), SubsidiaryBuider.create(), RequestType.LAMP_CHANGE,
					"burnt-out lamps", ContractBuilder.create(), LocalDate.now().minusDays(1), StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00103", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutStatusTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(), SubsidiaryBuider.create(), RequestType.GRASS_TRIMMING,
					"tall grams", ContractBuilder.create(), LocalDate.now().plusDays(1), null);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00104", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutInvalidStatusTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(), SubsidiaryBuider.create(), RequestType.GRASS_TRIMMING,
					"tall grams", ContractBuilder.create(), LocalDate.now().plusDays(1), StatusType.CANCELED);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00105", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutSubsidiaryTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(),
					null, RequestType.PAINTING,
					"moldy walls", ContractBuilder.create(), LocalDate.now().plusDays(1), StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00106", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutJustificationTest() {
		try {
			new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(),
					SubsidiaryBuider.create(), RequestType.PAINTING,
					"", ContractBuilder.create(), LocalDate.now().plusDays(1), StatusType.PENDING);

			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00107", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void validateRequestDateTest() {
		final MaintenanceRequest maintenanceRequest = new MaintenanceRequest(AuthorBuilder.create(), AuthorBuilder.create(),
				SubsidiaryBuider.create(), RequestType.PAINTING,
				"moldy walls", ContractBuilder.create(), LocalDate.now().plusDays(1), StatusType.PENDING);

		Assert.assertEquals(LocalDate.now(), maintenanceRequest.getRequestDate());
	}


}
