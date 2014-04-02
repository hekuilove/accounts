package org.quinn.accounts.controller.acc;

import org.quinn.accounts.controller.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
