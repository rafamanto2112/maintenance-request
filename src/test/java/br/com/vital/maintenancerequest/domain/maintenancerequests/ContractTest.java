package br.com.vital.maintenancerequest.domain.maintenancerequests;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vital.maintenancerequest.domain.DomainException;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.Contract;

@RunWith(MockitoJUnitRunner.class)
public class ContractTest {

	@Test
	public void successCreatingTest() {

		final Contract contract = new Contract(231243434L, "Tiho Services", "28381059000196", "Thomas", LocalDate.now().plusYears(3));

		Assert.assertNotNull(contract.getNumber());
		Assert.assertNotNull(contract.getName());
		Assert.assertNotNull(contract.getCnpj());
		Assert.assertNotNull(contract.getManager());
		Assert.assertNotNull(contract.getEffectiveDate());
	}

	@Test
	public void creatingWithoutNumberTest() {
		try {
			new Contract(null, "Tiho Services", "28381059000196", "Thomas", LocalDate.now().plusYears(3));
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00301", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutNameTest() {
		try {
			new Contract(231243434L, "", "28381059000196", "Thomas", LocalDate.now().plusYears(3));
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00302", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutEffetiveDateTest() {
		try {
			new Contract(231243434L, "Tiho Services", "28381059000196", "Thomas", null);
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00303", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithExpiratedEffetiveDateTest() {
		try {
			new Contract(231243434L, "Tiho Services", "28381059000196", "Thomas", LocalDate.now().minusDays(1));
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00304", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}


	@Test
	public void creatingWithoutCnpjTest() {
		try {
			new Contract(231243434L, "Tiho Services", null, "Thomas", LocalDate.now().plusYears(3));
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00305", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithInvalidCnpjTest() {
		try {
			new Contract(231243434L, "Tiho Services", "123", "Thomas",LocalDate.now().plusYears(3));
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00306", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

	@Test
	public void creatingWithoutManager() {
		try {
			new Contract(231243434L, "Tiho Services", "28381059000196", "", LocalDate.now().plusYears(3));
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00307", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());

		}
	}


}
