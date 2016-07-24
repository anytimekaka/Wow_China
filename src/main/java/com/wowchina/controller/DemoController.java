package com.wowchina.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wowchina.model.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class DemoController {

	@Value("${uploadFile.dir}")
	private String uploadFilDir;

    @Autowired
    private HttpServletRequest request;

	@RequestMapping(value = "/demoftl.action", method = RequestMethod.GET)
	public String demoftl(Model model) {
		model.addAttribute("hello", "hello world");
		return "demo";
	}

	@RequestMapping(value = "/demo.action", method = RequestMethod.GET)
	public @ResponseBody  CommonResponse demo(HttpServletRequest request,
			Model model) {
		CommonResponse resp = new CommonResponse();
		System.out.println(this.uploadFilDir);
		return resp;
	}

    @RequestMapping(value = "/fileUpload.action", method = RequestMethod.POST)
    public @ResponseBody String fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") int userId, @RequestParam("token") String token) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
//                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
//                        + file.getOriginalFilename();
                // 转存文件
                String filePath = "/Users/wangguisheng/workspace/project/" + file.getOriginalFilename();
                System.out.println(filePath);
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 重定向
        return "success";
    }
}
