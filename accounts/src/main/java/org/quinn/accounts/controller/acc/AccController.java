package org.quinn.accounts.controller.acc;

import org.quinn.accounts.controller.AbstractController;
import org.quinn.accounts.model.acc.Income;
import org.quinn.accounts.util.paginate.Paginate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;

@Controller
@RequestMapping(value = "acc")
public class AccController extends AbstractController {


	@RequestMapping(value = "index")
	public String index() {
		return "/acc/index";
	}

	@RequestMapping(value = "consumeRecord")
	public String consumeRecord() {
		return "/acc/consumeRecord";
	}

	@RequestMapping(value = "incomeRecord")
	public ModelAndView incomeRecord(Paginate<Income> pageBean,Income bean) {
		ModelAndView mav = new ModelAndView();
		pageBean = this.incomeService.paginateIncome(pageBean);
		mav.setViewName("/acc/incomeRecord");
		pageBean.setBean(bean);
		mav.addObject(super.pageKey, pageBean);
		return mav;
	}
}
