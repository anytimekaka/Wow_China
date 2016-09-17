package com.wowchina.controller;

import com.wowchina.domain.Apply;
import com.wowchina.domain.PostItem;
import com.wowchina.domain.User;
import com.wowchina.domain.UserInfo;
import com.wowchina.model.AddPostRequest;
import com.wowchina.model.CommonResponse;
import com.wowchina.model.EditUserInfoRequest;
import com.wowchina.model.PostListRequest;
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
import java.util.List;

@Controller
public class UserController {

	@Value("${uploadFile.dir}")
	private String uploadFileDir;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/getPostMessageList.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse getPostMessageList(@RequestParam int userId,
                                                @RequestParam String token,
                                                Model model){
        if(!this.userService.checkUser(userId, token)){
            return CommonResponse.authErrorResponse();
        }
        return userService.getPostMessageList(userId);
    }

    @RequestMapping(value = "/getApplyMessageList.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse getApplyMessageList(@RequestParam int userId,
                                                @RequestParam String token,
                                                Model model) {
        if(!this.userService.checkUser(userId, token)){
            return CommonResponse.authErrorResponse();
        }
        return userService.getApplyMessageList(userId);
    }

    /**
     * 发布者接受接口
     * @param userId
     * @param token
     * @param messageId
     * @param relatedId
     * @param model
     * @return
     */
    @RequestMapping(value = "/accept.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse accept(@RequestParam int userId,
                                               @RequestParam String token,
                                               @RequestParam int messageId,
                                               @RequestParam int relatedId,
                                               Model model) {
        if(!this.userService.checkUser(userId, token)){
            return CommonResponse.authErrorResponse();
        }
        return userService.accept(messageId, relatedId);
    }

    /**
     * 发布者拒绝接口
     * @param userId
     * @param token
     * @param messageId
     * @param relatedId
     * @param model
     * @return
     */
    @RequestMapping(value = "/refuse.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse refuse(@RequestParam int userId,
                                                @RequestParam String token,
                                                @RequestParam int messageId,
                                                @RequestParam int relatedId,
                                                Model model) {
        if(!this.userService.checkUser(userId, token)){
            return CommonResponse.authErrorResponse();
        }
        return userService.refuse(messageId, relatedId);
    }

    /**
     * 申请者申请接口
     * @param userId
     * @param token
     * @param postId
     * @param model
     * @return
     */
    @RequestMapping(value = "/apply.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse apply(@RequestParam int userId,
                                               @RequestParam String token,
                                               @RequestParam int postId,
                                               Model model) {
        if(!this.userService.checkUser(userId, token)){
            return CommonResponse.authErrorResponse();
        }
        Apply apply = new Apply();
        apply.setPostId(postId);
        apply.setUserId(userId);
        return userService.apply(apply);
    }

    /**
     * 查询用户的所有post列表
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/getPostListByUserId.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse getPostListByUserId(@RequestParam int userId,
                                               Model model) {
        return postService.getPostListByUserId(userId);
    }

    /**
     * 首页post列表
     * @param industryId
     * @param currentPage
     * @param pageSize
     * @param model
     * @return
     */
    @RequestMapping(value = "/postlist.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse postListByIndustryId(@RequestParam int industryId,
                                               @RequestParam int currentPage,
                                               @RequestParam int pageSize,
                                               Model model) {
        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setIndustryId(industryId);
        postListRequest.setCurrentPage(currentPage);
        postListRequest.setPageSize(pageSize);
        List<PostItem> list = this.userService.getPostsByIndustryId(postListRequest);
        CommonResponse response = CommonResponse.successResponse();
        response.setResult(list);
        return response;
    }

    @RequestMapping(value = "/search.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse searchByCityId(@RequestParam(required = false) int cityId,
                                                        @RequestParam(required = false) int majorId,
                                                        @RequestParam(required = false) String keyword,
                                                        @RequestParam int currentPage,
                                                        @RequestParam int pageSize,
                                                        Model model) {

        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setCityId(cityId);
        postListRequest.setMajorId(majorId);
        postListRequest.setKeyword(keyword);
        postListRequest.setCurrentPage(currentPage);
        postListRequest.setPageSize(pageSize);
        List<PostItem> list = this.userService.queryPostsByAllConditions(postListRequest);
        CommonResponse response = CommonResponse.successResponse();
        response.setResult(list);
        return response;
    }

    @RequestMapping(value = "/searchByCityId.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse searchByCityId(@RequestParam int cityId,
                                               @RequestParam int currentPage,
                                               @RequestParam int pageSize,
                                               Model model) {
        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setCityId(cityId);
        postListRequest.setCurrentPage(currentPage);
        postListRequest.setPageSize(pageSize);
        List<PostItem> list = this.userService.queryPostsByCityId(postListRequest);
        CommonResponse response = CommonResponse.successResponse();
        response.setResult(list);
        return response;
    }

    @RequestMapping(value = "/searchByKeyword.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse searchByKeyword(@RequestParam String keyword,
                                               @RequestParam int currentPage,
                                               @RequestParam int pageSize,
                                               Model model) {
        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setKeyword(keyword);
        postListRequest.setCurrentPage(currentPage);
        postListRequest.setPageSize(pageSize);
        List<PostItem> list = this.userService.queryPostsByKeyword(postListRequest);
        CommonResponse response = CommonResponse.successResponse();
        response.setResult(list);
        return response;
    }

    @RequestMapping(value = "/searchByMajorId.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse searchByMajorId(@RequestParam int majorId,
                                               @RequestParam int currentPage,
                                               @RequestParam int pageSize,
                                               Model model) {
        PostListRequest postListRequest = new PostListRequest();
        postListRequest.setMajorId(majorId);
        postListRequest.setCurrentPage(currentPage);
        postListRequest.setPageSize(pageSize);
        List<PostItem> list = this.userService.queryPostsByMajor(postListRequest);
        CommonResponse response = CommonResponse.successResponse();
        response.setResult(list);
        return response;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param model
     * @return
     */
	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public @ResponseBody  CommonResponse login(@RequestParam String username,
                                               @RequestParam String password,
                                               Model model) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return this.userService.login(user);
	}

    /**
     * 用户注册
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/register.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse register(@RequestParam String username,
                                                  @RequestParam String password,
                                                  Model model){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return this.userService.register(user);
    }

    /**
     * 根据ID，预览用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/viewUserInfo.action", method = RequestMethod.GET)
    public @ResponseBody CommonResponse<UserInfo> viewUserInfo(@RequestParam int userId){
        return this.userService.queryUserInfoByUserId(userId);
    }

    /**
     * 用户编辑信息
     * @param resq
     * @return
     */
    @RequestMapping(value = "/editUserInfo.action", method = RequestMethod.POST)
    public @ResponseBody CommonResponse editUserInfo(@RequestBody EditUserInfoRequest resq){
        if(!this.userService.checkUser(resq.getUserId(), resq.getToken())){
            return CommonResponse.authErrorResponse();
        }
        return this.userService.updateUserInfo(resq);
    }

    /**
     * 添加Post
     * @param req
     * @return
     */
    @RequestMapping(value = "/addPost.action", method = RequestMethod.POST)
    public @ResponseBody CommonResponse addPost(@RequestBody AddPostRequest req){
        if(!this.userService.checkUser(req.getUserId(), req.getToken())){
            return CommonResponse.authErrorResponse();
        }
        return this.postService.addPost(req);
    }

    /**
     * 更新Post信息
     * @param req
     * @return
     */
    @RequestMapping(value = "/updatePost.action", method = RequestMethod.POST)
    public @ResponseBody CommonResponse updatePost(@RequestBody AddPostRequest req){
        if(!this.userService.checkUser(req.getUserId(), req.getToken())){
            return CommonResponse.authErrorResponse();
        }
        return this.postService.updatePost(req);
    }

    /**
     * 根据postID，获取post详细信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPostById.action", method = RequestMethod.GET)
    public @ResponseBody  CommonResponse getPostInfoById(@RequestParam int id){
        return this.postService.getPostInfoById(id);
    }

    /**
     * 用户上传头像图片
     * @param file
     * @param userId
     * @param token
     * @return
     */
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
                fileDBName = fileDBName + fileType;
                String filePath = this.uploadFileDir + fileDBName;
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
