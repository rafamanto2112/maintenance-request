package br.com.vital.maintenancerequest.application.maintenanceRequests;

import br.com.vital.maintenancerequest.domain.maintenanceRequests.Author;

public class AuthorFactory {

	public static Author create(RequesterDto requester) {
		return new Author(requester.getId(), requester.getName());
	}

}
