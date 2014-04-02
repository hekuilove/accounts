package org.quinn.accounts.service.base.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.quinn.accounts.model.Permission;
import org.quinn.accounts.service.base.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Set<Permission> findByRoles(List<String> roleIds) {
		if (roleIds == null || roleIds.size() == 0)
			return null;
		final Set<Permission> permissions = new HashSet<Permission>();
		StringBuffer sql = new StringBuffer("SELECT A.* FROM t_permission A LEFT JOIN t_role_permission B  ");
		sql.append("ON A.PERMISSION_ID = B.PERMISSION_ID WHERE 1=1");
		sql.append(" AND B.ROLE_ID IN (");
		for (int i = 0; i < roleIds.size(); i++)
			sql.append("?,");
		sql.delete(sql.length() -1, sql.length() );
		sql.append(") GROUP BY A.permission_id");
		this.jdbcTemplate.query(sql.toString(), roleIds.toArray(), new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				Permission per = new Permission();
				per.setPermissionId(rs.getString("PERMISSION_ID"));
				per.setPermissionName(rs.getString("PERMISSION_NAME"));
				per.setPermissionType(rs.getInt("PERMISSION_TYPE"));
				per.setRequestMethod(rs.getString("REQUEST_METHOD"));
				permissions.add(per);
			}
		});
		return permissions;
	}
}
