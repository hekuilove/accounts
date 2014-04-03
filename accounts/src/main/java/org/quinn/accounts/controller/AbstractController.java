package org.quinn.accounts.controller;

import org.quinn.accounts.service.acc.IComsumeService;
import org.quinn.accounts.service.acc.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractController {


	protected String pageKey = "paginate";

	@Autowired
	protected IIncomeService incomeService;

	@Autowired
	protected IComsumeService comsumeService;
}
