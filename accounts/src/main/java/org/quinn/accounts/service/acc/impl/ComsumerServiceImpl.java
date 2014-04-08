package org.quinn.accounts.service.acc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.quinn.accounts.model.acc.ComsumeRecord;
import org.quinn.accounts.model.acc.ComsumeType;
import org.quinn.accounts.service.acc.IComsumeService;
import org.quinn.accounts.util.paginate.AccountsPaginateException;
import org.quinn.accounts.util.paginate.Paginate;
import org.quinn.accounts.util.paginate.PaginateParam;
import org.quinn.accounts.util.paginate.PaginateService;
import org.quinn.accounts.util.paginate.PaginateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ComsumerServiceImpl implements IComsumeService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addType(ComsumeType type) {
		String sql = "insert into t_comsume_type (id,type,createDate,remark)values(?,?,?,?)";
		this.jdbcTemplate.update(sql, type.getId(), type.getType(), type.getCreateDate(), type.getRemark());
	}

	@Override
	public void addRecord(ComsumeRecord record) {
		String sql = "insert into t_comsume_record (id,createDate,remark,comsume_type,comsume_money)values(?,?,?,?,?)";
		this.jdbcTemplate.update(sql, record.getId(), record.getCreateDate(), record.getRemark(), record.getComsumeType(),
				record.getComsumeMoney());
	}

	@Override
	public Paginate<ComsumeRecord> paginateConsumeRecord(Paginate<ComsumeRecord> paginate) {

		StringBuffer sql = new StringBuffer(
				"SELECT A.*,B.* FROM t_comsume_record A LEFT JOIN t_comsume_type B ON A.comsume_type=B.id WHERE 1=1 ORDER BY A.CREATEDATE DESC LIMIT ?,?");
		StringBuffer countSql = new StringBuffer(
				"select count(*) from t_comsume_record A LEFT JOIN t_comsume_type B ON A.comsume_type=B.id WHERE 1=1");
		PaginateParam param = new PaginateParam();
		param.setPagParas(new Object[] { paginate.getLimit().getFirstPara(), paginate.getLimit().getNextPara() });
		try {
			PaginateUtils.paginate(this.jdbcTemplate, paginate, new PaginateService() {

				@Override
				public Object callback(ResultSet rs) {
					ComsumeRecord cr = new ComsumeRecord();
					ComsumeType ct = new ComsumeType();
					try {
						cr.setId(rs.getString("id"));
						cr.setComsumeMoney(rs.getDouble("comsume_money"));
						cr.setCreateBy(rs.getInt("createBy"));
						cr.setCreateDate(rs.getDate("createDate"));
						cr.setRemark(rs.getString("remark"));
						cr.setComsumeType(rs.getString("comsume_type"));
						ct.setId(rs.getString("comsume_type"));
						ct.setType(rs.getString("type"));
						cr.setType(ct);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return cr;
				}
			}, sql.toString(), countSql.toString(), param);
		} catch (AccountsPaginateException e) {
			e.printStackTrace();
		}
		return paginate;
	}

}
