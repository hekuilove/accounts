package org.quinn.accounts.controller;

import java.util.ArrayList;
import java.util.List;

import org.quinn.accounts.dao.IIncomeDAO;
import org.quinn.accounts.dao.mapper.IncomeMapper;
import org.quinn.accounts.util.paginate.PageBean;
import org.quinn.accounts.util.paginate.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping(value = "test2")
	public String test2() {
		System.out.println(this.incomeDAO.findByPk("02b3b01ed14b4dd495a8292b648ac97e"));
		return "/test/test1";
	}

	@RequestMapping(value = "pag1")
	public ModelAndView pag1(PageBean<TestUser> pb) {
		ModelAndView mav = new ModelAndView("/test/pag1");
		int fromInd = pb.getPageSize() * (pb.getPageNo() - 1);
		fromInd = fromInd == 0 ? fromInd : fromInd - 1;
		int toInd = fromInd + pb.getPageSize();
		toInd = toInd > USERS.size() ? USERS.size() : toInd;
		List<TestUser> users = this.getUser(fromInd, toInd);
		pb.setDatas(users);
		pb.setPageAllSize(USERS.size());
		mav.addObject(super.pageKey, pb);
		pb.initFooter();
		return mav;
	}
	
	@RequestMapping(value = "pag22")
	public String pag22() {
		return "/test/pag2";
	}
	
	

	@RequestMapping(value = "pag2")
	@ResponseBody
	public PageBean<TestUser> pag2(PageBean<TestUser> pb) {
		int fromInd = pb.getPageSize() * (pb.getPageNo() - 1);
		fromInd = fromInd == 0 ? fromInd : fromInd - 1;
		int toInd = fromInd + pb.getPageSize();
		toInd = toInd > USERS.size() ? USERS.size() : toInd;
		List<TestUser> users = this.getUser(fromInd, toInd);
		pb.setDatas(users);
		pb.setPageAllSize(USERS.size());
		pb.initFooter();
		return pb;
	}

	private List<TestUser> getUser(int fromInd, int toInd) {
		return USERS.subList(fromInd, toInd);
	}

	private static List<TestUser> USERS = new ArrayList<TestUser>();
	static {
		for (int i = 1; i < 75; i++) {
			TestUser user = new TestUser();
			user.setAddress("上海" + i + "村");
			user.setAge(i);
			user.setDepartment("第" + i + "部门");
			user.setName("江狗" + i);
			USERS.add(user);
		}
	}
}
