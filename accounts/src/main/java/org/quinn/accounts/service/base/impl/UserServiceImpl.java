package org.quinn.accounts.service.base.impl;

import java.util.Map;

import org.quinn.accounts.model.User;
import org.quinn.accounts.service.base.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT * FROM T_USER WHERE USERNAME=?";
		Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, username);
		if (map != null) {
			User us = new User();
			us.setUsername((String) map.get("USERNAME"));
			us.setPassword((String) map.get("PASSWORD"));
			return us;
		}
		return null;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		String sql = "SELECT * FROM T_USER WHERE USERNAME=? AND PASSWORD=?";
		Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, username, password);
		if (map != null) {
			User us = new User();
			us.setUsername((String) map.get("USERNAME"));
			us.setPassword((String) map.get("PASSWORD"));
			return us;
		}
		return null;
	}

}
