package br.com.vital.maintenancerequest.domain.subsidiaries;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vital.maintenancerequest.domain.DomainException;

@RunWith(MockitoJUnitRunner.class)
public class SubsidiaryTest {

	@Test
	public void creatingSubsidiaryTest() throws Exception {
		final Subsidiary subsidiary = new Subsidiary("New York");

		Assert.assertEquals("New York", subsidiary.getName());
		Assert.assertFalse(subsidiary.getId().isEmpty());
	}

	@Test
	public void creatingWithoutName() {
		try {
			new Subsidiary("");
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00401", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());
		}
	}

}
