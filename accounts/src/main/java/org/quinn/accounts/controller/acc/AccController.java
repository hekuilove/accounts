package org.quinn.accounts.controller.acc;

import java.util.Date;

import org.quinn.accounts.controller.AbstractController;
import org.quinn.accounts.model.acc.ComsumeRecord;
import org.quinn.accounts.model.acc.Income;
import org.quinn.accounts.util.DateParam;
import org.quinn.accounts.util.StringUtils;
import org.quinn.accounts.util.paginate.Paginate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

@Controller
@RequestMapping(value = "acc")
public class AccController extends AbstractController {

	@RequestMapping(value = "index")
	public String index() {
		return "/acc/index";
	}

	@RequestMapping(value = "consumeRecord")
	public ModelAndView consumeRecord(Paginate<ComsumeRecord> paginate, ComsumeRecord cr) {
		paginate.setBean(cr);
		this.comsumeService.paginateConsumeRecord(paginate);
		ModelAndView mav = new ModelAndView("/acc/consumeRecord");
		mav.addObject(super.pageKey, paginate);
		return mav;
	}

	@RequestMapping(value = "incomeRecord")
	public ModelAndView incomeRecord(Paginate<Income> pageBean, Income bean, DateParam date) {
		ModelAndView mav = new ModelAndView();
		pageBean.setBean(bean);
		pageBean = this.incomeService.paginateIncome(pageBean, date);
		mav.setViewName("/acc/incomeRecord");
		mav.addObject(super.pageKey, pageBean);
		mav.addObject("param", date);
		return mav;
	}

	@RequestMapping(value = "addIncome", method = RequestMethod.POST)
	public String addIncome(Income income) {
		income.setId(StringUtils.getUUID());
		income.setCreateDate(new Date());
		this.incomeService.addIncome(income);
		return "redirect:/acc/incomeRecord.shtml";
	}
}
