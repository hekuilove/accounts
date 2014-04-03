package org.quinn.accounts.service.acc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.quinn.accounts.model.acc.Income;
import org.quinn.accounts.service.acc.IIncomeService;
import org.quinn.accounts.util.paginate.AccountsPaginateException;
import org.quinn.accounts.util.paginate.Paginate;
import org.quinn.accounts.util.paginate.PaginateParam;
import org.quinn.accounts.util.paginate.PaginateService;
import org.quinn.accounts.util.paginate.Paginate.MySQLLimitParam;
import org.quinn.accounts.util.paginate.PaginateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IIncomeService {


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Paginate<Income> paginateIncome(Paginate<Income> pageBean) {
		MySQLLimitParam paras = pageBean.getLimit();
		String sql = "SELECT * FROM T_INCOME  ORDER BY CREATEDATE DESC LIMIT ?,?";
		String countSql = "SELECT COUNT(*) FROM T_INCOME";
		PaginateParam param = new PaginateParam();
		param.setPagParas(new Object[] { paras.getFirstPara(), paras.getNextPara() });
		try {
			PaginateUtils.paginate(this.jdbcTemplate, pageBean, new PaginateService() {


				@Override
				public Object callback(ResultSet rs) {
					Income income = new Income();
					try {
						income.setCreateBy(rs.getInt("createBy"));
						income.setCreateDate(rs.getDate("createDate"));
						income.setId(rs.getString("id"));
						income.setRemark(rs.getString("remark"));
						income.setSumMoney(rs.getDouble("sumMoney"));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return income;
				}
			}, sql, countSql, param);
		} catch (AccountsPaginateException e) {
			e.printStackTrace();
		}
		return pageBean;
	}
}
