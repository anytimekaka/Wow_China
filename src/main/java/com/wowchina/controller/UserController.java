package com.wowchina.controller;

import com.wowchina.domain.User;
import com.wowchina.domain.UserInfo;
import com.wowchina.model.AddPostRequest;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.EditUserInfoRequest;
import com.wowchina.service.PostService;
import com.wowchina.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

@Controller
public class UserController {

	@Value("${uploadFile.dir}")
	private String uploadFilDir;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private HttpServletRequest request;

	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public @ResponseBody  CommonResponse login(@RequestParam String username,
                                               @RequestParam String password,
                                               Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return this.userService.login(user);
	}

    @RequestMapping(value = "/register.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse register(@RequestParam String username,
                                                  @RequestParam String password,
                                                  Model model){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return this.userService.register(user);
    }

    @RequestMapping(value = "/viewUserInfo.action", method = RequestMethod.GET)
    public @ResponseBody CommonResponse<UserInfo> viewUserInfo(@RequestParam int userId){
        return this.userService.queryUserInfoByUserId(userId);
    }

    @RequestMapping(value = "/editUserInfo.action", method = RequestMethod.POST)
    public @ResponseBody CommonResponse editUserInfo(@RequestBody EditUserInfoRequest resq){
        if(!this.userService.checkUser(resq.getUserId(), resq.getToken())){
            return CommonResponse.authErrorResponse();
        }
        return this.userService.updateUserInfo(resq);
    }

    @RequestMapping(value = "/addPost.action", method = RequestMethod.POST)
    public @ResponseBody CommonResponse addPost(@RequestBody AddPostRequest req){
        if(!this.userService.checkUser(req.getUserId(), req.getToken())){
            return CommonResponse.authErrorResponse();
        }
        return this.postService.addPost(req);
    }

    @RequestMapping(value = "/getPostById.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse getPostInfoById(@RequestParam int id){
        return this.postService.getPostInfoById(id);
    }

    @RequestMapping(value = "/uploadUserImage.action", method = RequestMethod.POST)
    public @ResponseBody CommonResponse fileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("userId") int userId,
                                                   @RequestParam("token") String token) {
        CommonResponse response = CommonResponse.successResponse();
        if(!userService.checkUser(userId, token)){
            return CommonResponse.authErrorResponse();
        }
        String fileDBName = Long.toString(new Date().getTime());
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
                String filePath = this.uploadFilDir + fileDBName + fileType;
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
                response = CommonResponse.errorResponse("上传文件出现异常");
            }
        }
        response = this.userService.updateUserImageInfo(userId, fileDBName);
        return response;
    }
}
