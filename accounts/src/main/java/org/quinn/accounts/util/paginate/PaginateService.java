package org.quinn.accounts.util.paginate;

import java.sql.ResultSet;

public interface PaginateService {


	Object callback(ResultSet rs);
}
