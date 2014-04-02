package org.quinn.accounts.service.acc.impl;

import org.quinn.accounts.model.acc.ComsumeRecord;
import org.quinn.accounts.model.acc.ComsumeType;
import org.quinn.accounts.service.acc.IComsumeService;
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

}
