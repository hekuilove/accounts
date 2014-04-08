package org.quinn.accounts.dao.impl;

import org.quinn.accounts.dao.IIncomeDAO;
import org.quinn.accounts.dao.mapper.IncomeMapper;
import org.quinn.accounts.model.acc.Income;
import org.springframework.stereotype.Repository;

@Repository
public class IncomeDAOImpl extends MybatisDAOImpl<Income, IncomeMapper> implements IIncomeDAO {

}
