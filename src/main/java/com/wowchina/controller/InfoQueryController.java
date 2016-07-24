package com.wowchina.controller;

import com.wowchina.domain.User;
import com.wowchina.model.CommonResponse;
import com.wowchina.service.InfoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InfoQueryController {

    @Autowired
    private InfoQueryService infoQueryService;

	@RequestMapping(value = "/queryMajor.action", method = RequestMethod.GET)
	public @ResponseBody  CommonResponse queryMajor(HttpServletRequest request,
                                               Model model) {
        return this.infoQueryService.queryAllMajor();
	}

    @RequestMapping(value = "/queryIndustry.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse queryAllIndustry(HttpServletRequest request,
                                               Model model){
        return this.infoQueryService.queryAllIndustry();
    }

}
