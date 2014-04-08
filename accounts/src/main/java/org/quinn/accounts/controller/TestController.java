package org.quinn.accounts.controller;

import org.quinn.accounts.dao.IIncomeDAO;
import org.quinn.accounts.dao.mapper.IncomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "test")
@Controller
public class TestController extends AbstractController {

	@Autowired
	private IncomeMapper incomeMapper;
	
	@Autowired
	private IIncomeDAO incomeDAO;

	@RequestMapping(value = "test1")
	public String test1() {
		System.out.println(this.incomeMapper.findAll());
		return "/test/test1";
	}
	
	@RequestMapping(value="test2")
	public String test2(){
		System.out.println(this.incomeDAO.findByPk("02b3b01ed14b4dd495a8292b648ac97e"));
		return "/test/test1";
	}
}
