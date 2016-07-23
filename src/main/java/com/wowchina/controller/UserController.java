package com.wowchina.controller;

import com.wowchina.domain.User;
import com.wowchina.model.CommonResponse;
import com.wowchina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	@Value("${uploadFile.dir}")
	private String uploadFilDir;

    @Autowired
    private UserService userService;

	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public @ResponseBody  CommonResponse login(HttpServletRequest request,
                                               @RequestParam String username,
                                               @RequestParam String password,
                                               Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return this.userService.login(user);
	}

    @RequestMapping(value = "/register.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse register(HttpServletRequest request,
                                               @RequestParam String username,
                                               @RequestParam String password,
                                               Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return this.userService.register(user);
    }

}
