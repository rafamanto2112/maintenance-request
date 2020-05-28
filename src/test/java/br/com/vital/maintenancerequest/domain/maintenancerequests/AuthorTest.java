package br.com.vital.maintenancerequest.domain.maintenancerequests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.vital.maintenancerequest.domain.DomainException;
import br.com.vital.maintenancerequest.domain.maintenanceRequests.Author;

@RunWith(MockitoJUnitRunner.class)
public class AuthorTest {

	@Test
	public void successCreatingTest() {
		final Author author = new Author(837489374, "Thomas Cat");

		Assert.assertEquals("Thomas Cat", author.getName());
		Assert.assertEquals(Integer.valueOf(837489374), author.getIdentify());
	}

	@Test
	public void creatingWithoutIdentifier() {
		try {
			new Author(null, "Thomas Cat");
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00201", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());

		}
	}

	@Test
	public void creatingWithoutIdentifierName() {
		try {
			new Author(3423434, "");
			Assert.fail();
		} catch(final DomainException e) {
			Assert.assertEquals("D00202", e.getCode());
			Assert.assertTrue(e.getMessage() != null && !e.getMessage().trim().isEmpty());

		}
	}

}
