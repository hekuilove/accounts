package org.quinn.accounts.util.paginate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PaginateUtils {

	public static <T> Paginate<T> paginate(JdbcTemplate jdbcTemplate, Paginate<T> page, final PaginateService paginateService,
			String sql, String countSql, PaginateParam param) throws AccountsPaginateException {
		if (jdbcTemplate == null || page == null)
			throw new AccountsPaginateException("JdbcTemplate or  Paginate connot null");
		long pageAllSize = jdbcTemplate.queryForObject(countSql, param.getComParas(), Long.class);
		page.setPageAllSize((int) pageAllSize);
		final List<T> datas = new ArrayList<T>(page.getPageSize());
		jdbcTemplate.query(sql, param.getAllParams(), new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Object o = paginateService.callback(rs);
				datas.add((T) o);
				return o;
			}
		});
		page.setDatas(datas);
		return page;
	}
}
