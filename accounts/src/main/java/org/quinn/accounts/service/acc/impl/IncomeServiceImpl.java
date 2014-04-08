package org.quinn.accounts.service.acc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quinn.accounts.model.acc.Income;
import org.quinn.accounts.service.acc.IIncomeService;
import org.quinn.accounts.util.DateParam;
import org.quinn.accounts.util.StringUtils;
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
	public Paginate<Income> paginateIncome(Paginate<Income> pageBean, DateParam date) {
		MySQLLimitParam paras = pageBean.getLimit();
		StringBuffer sql = new StringBuffer("SELECT * FROM T_INCOME WHERE 1=1 ");
		StringBuffer countSql = new StringBuffer("SELECT COUNT(*) FROM T_INCOME WHERE 1=1 ");
		PaginateParam param = new PaginateParam();
		if (date != null) {
			if (StringUtils.isNotBlank(date.getTime1()) || StringUtils.isNotBlank(date.getTime2())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sql.append("AND CREATEDATE BETWEEN ? AND ? ");
				countSql.append("AND CREATEDATE BETWEEN ? AND ?");
				param.addComParm(DateParam.parseStr2Date(date.getTime1(), sdf, new Date(0)));
				param.addComParm(DateParam.parseStr2Date(date.getTime2(), sdf, new Date()));

			}
			if (pageBean.getBean() != null && pageBean.getBean().getCreateBy() != null) {
				sql.append(" AND CREATEBY = ?");
				countSql.append(" AND CREATEBY = ?");
				param.addComParm(pageBean.getBean().getCreateBy());
			}
		}
		sql.append("  ORDER BY CREATEDATE DESC LIMIT ?,?");

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
			}, sql.toString(), countSql.toString(), param);
		} catch (AccountsPaginateException e) {
			e.printStackTrace();
		}
		return pageBean;
	}

	@Override
	public void addIncome(Income income) {
		String sql = "INSERT INTO T_INCOME (ID,CREATEBY,CREATEDATE,REMARK,SUMMONEY)VALUES(?,?,?,?,?)";
		this.jdbcTemplate.update(sql, income.getId(), income.getCreateBy(), income.getCreateDate(), income.getRemark(),
				income.getSumMoney());
	}
}
