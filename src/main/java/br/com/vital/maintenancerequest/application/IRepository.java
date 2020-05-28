package br.com.vital.maintenancerequest.application;

import br.com.vital.maintenancerequest.domain.Entity;

public interface IRepository<T extends Entity> {

	T getById(Integer id);
	void insert(T entity);
}
