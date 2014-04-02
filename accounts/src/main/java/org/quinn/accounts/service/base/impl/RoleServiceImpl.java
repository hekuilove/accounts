package org.quinn.accounts.service.base.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.quinn.accounts.model.Role;
import org.quinn.accounts.service.base.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Role> findByUsername(String username) {
		String sql = "SELECT B.* FROM T_USER_ROLE A INNER JOIN t_role B ON A.role_id = B.role_id WHERE USERNAME=?";
		final List<Role> roles = new ArrayList<Role>();
		this.jdbcTemplate.query(sql, new Object[] { username }, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				Role role = new Role();
				role.setRoleId(rs.getString("ROLE_ID"));
				role.setRoleName(rs.getString("ROLE_NAME"));
				roles.add(role);
			}

		});
		return roles;
	}

}
