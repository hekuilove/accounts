package org.quinn.accounts.controller.base;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.quinn.accounts.controller.AbstractController;
import org.quinn.accounts.shiro.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "usr")
public class LoginController extends AbstractController {

	private Logger log = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginG() {
		Subject subject = ShiroUtils.getSubject();
		if (subject.isAuthenticated())
			return "/login/successTest";
		else
			return "/login/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Subject subject = ShiroUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		token.setRememberMe(true);
		token.setUsername(username);
		token.setPassword(password.toCharArray());
		subject.login(token);
		log.info(subject.hasRole("管理员"));
		log.info(subject.isPermitted("/user/getUser"));
		ModelAndView mav = new ModelAndView("/login/success");
		return mav;
	}

	@RequestMapping(value = "noAuthorize")
	public String noAuthorize() {
		return "/login/noAuthorize";
	}
	

	@RequestMapping(value = "successTest")
	@RequiresRoles(value = "admin")
	public String successTest() {
		Subject subject = ShiroUtils.getSubject();
		System.out.println(subject.isAuthenticated());
		subject.checkPermission("user:update");
		return "/login/successTest";
	}

	@RequiresRoles(value = "user1")
	@RequestMapping(value = "successTest2")
	public String successTest2() {
		Subject subject = ShiroUtils.getSubject();
		System.out.println(subject.isAuthenticated());
		subject.checkPermission("user:update");
		return "/login/successTest";
	}
}
