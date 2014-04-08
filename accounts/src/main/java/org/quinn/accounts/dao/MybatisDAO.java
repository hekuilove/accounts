package org.quinn.accounts.dao;

import java.util.List;

import org.quinn.accounts.util.paginate.Paginate;

public interface MybatisDAO<T> {

	List<T> findAll();

	Paginate<T> paginate(Paginate<?> pag);

	int removeByPk(Object pk);

	T findByPk(Object pk);

}
