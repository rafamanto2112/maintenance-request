package br.com.vital.maintenancerequest.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vital.maintenancerequest.utils.GuidGenerator;

@SpringBootTest
public class EntityTest {

	@Autowired
	private GuidGenerator guidGenerator;

	@Test
	public void generateGuidTest() {
		final String result = guidGenerator.generate();
		System.out.println(result);
		Assert.assertNotNull(result);
	}
}
