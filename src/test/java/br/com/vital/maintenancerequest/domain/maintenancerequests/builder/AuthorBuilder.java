package br.com.vital.maintenancerequest.domain.maintenancerequests.builder;

import br.com.vital.maintenancerequest.domain.maintenanceRequests.Author;

public class AuthorBuilder {

	public static Author create() {
		final Author author = new Author(87973847, "Author name");
		return author;
	}

}
