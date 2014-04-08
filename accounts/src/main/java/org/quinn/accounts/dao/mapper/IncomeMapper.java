package org.quinn.accounts.dao.mapper;

import java.util.List;

import org.quinn.accounts.model.acc.Income;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeMapper {

	List<Income> findAll();
	
	Income findByPk(String pk);
}
