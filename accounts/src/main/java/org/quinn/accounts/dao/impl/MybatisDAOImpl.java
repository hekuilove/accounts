package org.quinn.accounts.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.quinn.accounts.dao.MybatisDAO;
import org.quinn.accounts.util.paginate.Paginate;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisDAOImpl<T, M> implements MybatisDAO<T> {

	@Autowired
	protected SqlSessionFactoryBean sqlSessionFactory;

	@Autowired
	protected SqlSessionTemplate sessionTemplate;

	protected Class<T> tclazz;

	protected Class<M> mclazz;

	@SuppressWarnings("unchecked")
	public MybatisDAOImpl() {
		Type[] ty = (Type[]) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		this.mclazz = (Class<M>) ty[1];
		this.tclazz = (Class<T>) ty[0];
	}

	@Override
	public List<T> findAll() {
		return null;
	}

	@Override
	public Paginate<T> paginate(Paginate<?> pag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int removeByPk(Object pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T findByPk(Object pk) {
		String statement = this.mclazz.getName() + ".findByPk";
		return this.sessionTemplate.selectOne(statement, pk);
	}

}
