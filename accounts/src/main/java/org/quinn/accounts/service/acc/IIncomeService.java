package org.quinn.accounts.service.acc;

import org.quinn.accounts.model.acc.Income;
import org.quinn.accounts.util.paginate.Paginate;

public interface IIncomeService {


	Paginate<Income> paginateIncome(Paginate<Income> pageBean);
}
