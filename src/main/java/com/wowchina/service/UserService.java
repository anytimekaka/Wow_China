package com.wowchina.service;

import com.wowchina.dao.*;
import com.wowchina.domain.*;
import com.wowchina.enums.MessageStatusEnum;
import com.wowchina.enums.ResponseCodeEnum;
import com.wowchina.model.*;
import com.wowchina.util.Parse2MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangguisheng on 16/6/23.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IndustryDao industryDao;
    @Autowired
    private MajorDao majorDao;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private PostService postService;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private PostDao postDao;

    /**
     * 获取个人的Apply消息列表
     * @param userId
     * @return
     */
    public CommonResponse<List> getApplyMessageList(int userId){
        List<Integer> status = new ArrayList<Integer>();
        status.add(MessageStatusEnum.ACCEPTED.getCode()); // 已接受
        status.add(MessageStatusEnum.APPLIED.getCode()); //已申请
        status.add(MessageStatusEnum.REJECTED.getCode()); //已拒绝
        List<Message> applyMessageList = messageDao.queryMessageByUserIdAndStatus(userId, status);
        CommonResponse response = CommonResponse.successResponse("获取Apply消息列表成功");
        response.setResult(applyMessageList);
        return response;
    }

    /**
     * 获取个人的Post消息列表页
     * @param userId
     * @return
     */
    public CommonResponse<List> getPostMessageList(int userId){
        List<PostMessageResponse> responses = new ArrayList<PostMessageResponse>();

        List<Integer> unReplyStatus = new ArrayList<Integer>();
        unReplyStatus.add(MessageStatusEnum.UNREPLY.getCode()); // 未回复
        List<Message> unReplyMessageList = messageDao.queryMessageByUserIdAndStatus(userId, unReplyStatus);
        PostMessageResponse unReplys = new PostMessageResponse();
        unReplys.setStatus(MessageStatusEnum.UNREPLY.getCode());
        unReplys.setDesc(MessageStatusEnum.UNREPLY.getValue());
        unReplys.setMessages(unReplyMessageList);
        responses.add(unReplys);

        List<Integer> repliedStatus = new ArrayList<Integer>();
        repliedStatus.add(MessageStatusEnum.REPLIED.getCode()); // 已回复
        List<Message> repliedMessageList = messageDao.queryMessageByUserIdAndStatus(userId, repliedStatus);
        PostMessageResponse repieds = new PostMessageResponse();
        repieds.setStatus(MessageStatusEnum.REPLIED.getCode());
        repieds.setDesc(MessageStatusEnum.REPLIED.getValue());
        repieds.setMessages(repliedMessageList);
        responses.add(repieds);
        CommonResponse response = CommonResponse.successResponse("获取Post消息列表成功");
        response.setResult(responses);
        return response;
    }

    /**
     * 拒绝申请
     * @param postMessageId
     * @param applyMessageId
     * @return
     */
    public CommonResponse<String> refuse(int postMessageId, int applyMessageId){
        /**
         * 修改发布者消息：未回复->已回复
         * 修改申请者消息：已申请->被拒绝
         */
        Message postMessage = new Message();
        postMessage.setId(postMessageId);
        postMessage.setStatus(MessageStatusEnum.REPLIED.getCode()); // 设置成 已回复
        Message applyMessage = new Message();
        applyMessage.setId(applyMessageId);
        applyMessage.setStatus(MessageStatusEnum.REJECTED.getCode()); // 设置成 被拒绝
        messageDao.updateMessageById(postMessage);
        messageDao.updateMessageById(applyMessage);
        return CommonResponse.successResponse("拒绝成功");
    }

    /**
     * 接受申请
     * @param postMessageId
     * @param applyMessageId
     * @return
     */
    public CommonResponse<String> accept(int postMessageId, int applyMessageId){
        /**
         * 修改发布者消息：未回复->已回复
         * 修改申请者消息：已申请->已接受
         */
        Message postMessage = new Message();
        postMessage.setId(postMessageId);
        postMessage.setStatus(MessageStatusEnum.REPLIED.getCode()); // 设置成 已回复
        Message applyMessage = new Message();
        applyMessage.setId(applyMessageId);
        applyMessage.setStatus(MessageStatusEnum.ACCEPTED.getCode()); // 设置成 已接受
        messageDao.updateMessageById(postMessage);
        messageDao.updateMessageById(applyMessage);
        return CommonResponse.successResponse("接受成功");
    }
    /**
     * 申请post
     * @param apply
     * @return
     */
    public CommonResponse<String> apply(Apply apply){
        // 用户已申请
        if(applyDao.isUserApplied(apply)){
            return CommonResponse.errorResponse(ResponseCodeEnum.ALREADY_APPLY.getCode(), ResponseCodeEnum.ALREADY_APPLY.getValue());
        }
        // 用户是Post的发起者
        if(postService.isUserPoster(apply.getUserId(), apply.getPostId())){
            return CommonResponse.errorResponse(ResponseCodeEnum.USER_IS_POSTER.getCode(), ResponseCodeEnum.USER_IS_POSTER.getValue());
        }

        /**
         * 添加申请记录
         * 添加发布者消息：未回复
         * 添加申请者消息：已申请
         */
        applyDao.addApply(apply);
        Message postMessage = new Message();
        Post post = postDao.getPostInfoById(apply.getPostId());
        postMessage.setUserId(post.getUserid());
        postMessage.setPostId(apply.getPostId());
        postMessage.setStatus(MessageStatusEnum.UNREPLY.getCode()); // 未回复
        int postMessageId = messageDao.addMessage(postMessage);
        Message applyMessage = new Message();
        applyMessage.setUserId(apply.getUserId());
        applyMessage.setPostId(apply.getPostId());
        applyMessage.setStatus(MessageStatusEnum.APPLIED.getCode()); // 已申请
        applyMessage.setRelatedId(postMessageId);
        int applyMessageId = messageDao.addMessage(applyMessage);

        // 更新关联的messageId
        postMessage.setRelatedId(applyMessageId);
        postMessage.setId(postMessageId);
        messageDao.updateMessageById(postMessage);
        applyMessage.setRelatedId(postMessageId);
        applyMessage.setId(applyMessageId);
        messageDao.updateMessageById(applyMessage);
        return CommonResponse.successResponse("申请成功");
    }

    /**
     * 用户是否可以申请此post
     * @param apply
     * @return true:能申请 false：不能申请
     */
    private boolean isUserCanApply(Apply apply){
        // 用户已经申请 或者 用户是post的发起者
        if(applyDao.isUserApplied(apply) || postService.isUserPoster(apply.getUserId(), apply.getPostId())){
            return false;
        }
        return true;
    }

    /**
     * 根据Industry分类，查询post列表
     * @param request
     * @return
     */
    public List<PostItem> getPostsByIndustryId(PostListRequest request){
        PostListParam param = new PostListParam();
        param.setIndustryId(request.getIndustryId());
        param.setLow((request.getCurrentPage()-1)*request.getPageSize());
        param.setHigh(request.getCurrentPage()*request.getPageSize());
        return userDao.queryPostsByIndustryId(param);
    }

    public List<PostItem> queryPostsByCityId(PostListRequest request){
        PostListParam param = new PostListParam();
        param.setCityId(request.getCityId());
        param.setLow((request.getCurrentPage()-1)*request.getPageSize());
        param.setHigh(request.getCurrentPage()*request.getPageSize());
        return userDao.queryPostsByCityId(param);
    }

    public List<PostItem> queryPostsByKeyword(PostListRequest request){
        PostListParam param = new PostListParam();
        param.setKeyword("%" + request.getKeyword() + "%");
        param.setLow((request.getCurrentPage()-1)*request.getPageSize());
        param.setHigh(request.getCurrentPage()*request.getPageSize());
        return userDao.queryPostsByKeyword(param);
    }

    public List<PostItem> queryPostsByMajor(PostListRequest request){
        PostListParam param = new PostListParam();
        param.setMajorId(request.getMajorId());
        param.setLow((request.getCurrentPage()-1)*request.getPageSize());
        param.setHigh(request.getCurrentPage()*request.getPageSize());
        return userDao.queryPostsByMajor(param);
    }

    public List<PostItem> queryPostsByAllConditions(PostListRequest request){
        PostListParam param = new PostListParam();
        param.setMajorId(request.getMajorId());
        if(null == request.getKeyword()){
            request.setKeyword("");
        }
        param.setKeyword("%" + request.getKeyword() + "%");
        param.setCityId(request.getCityId());
        param.setLow((request.getCurrentPage()-1)*request.getPageSize());
        param.setHigh(request.getCurrentPage()*request.getPageSize());
        return userDao.queryPostsByAllConditions(param);
    }

    /**
     * check用户是否合法
     * @param userId
     * @param token
     * @return true:合法
     */
    public boolean checkUser(int userId, String token){
        User user = this.userDao.queryUserInfoByUserId(userId);
        return null != user && user.getToken().equals(token);
    }

    /**
     * 根据UserId查询用户基本信息
     * @param userId
     * @return
     */
    public CommonResponse<UserInfo> queryUserInfoByUserId(int userId){
        User user = this.userDao.queryUserInfoByUserId(userId);
        if(null != user){
            List<Industry> industryList = this.industryDao.queryIndustrysByIds(user.getIndustryids().split(","));
            Major major = this.majorDao.queryMajorById(user.getMajorid());
            UserInfo userInfo = new UserInfo();
            userInfo.setMajor(major);
            userInfo.setIndustrys(industryList);
            userInfo.setId(userId);
            userInfo.setCertificates(user.getCertificates());
            userInfo.setEmail(user.getEmail());
            userInfo.setFacebook(user.getFacebook());
            userInfo.setLinkedinid(user.getLinkedinid());
            userInfo.setLinkedinprofileurl(user.getLinkedinprofileurl());
            userInfo.setLinkedinusername(user.getLinkedinusername());
            userInfo.setLocation(user.getLocation());
            userInfo.setRealname(user.getRealname());
            userInfo.setSkills(user.getSkills());
            userInfo.setTel(user.getTel());
            userInfo.setUniversity(user.getUniversity());
            userInfo.setUserimage(user.getUserimage());

            CommonResponse<UserInfo> response = CommonResponse.successResponse();
            response.setResult(userInfo);

            return response;
        }
        return CommonResponse.errorResponse("no user with userId: " + userId);
    }

    /**
     * 更新用户图片信息
     * @param userId
     * @param userimage
     * @return
     */
    public CommonResponse updateUserImageInfo(int userId, String userimage){
        User user = new User();
        user.setId(userId);
        user.setUserimage(userimage);
        int count = this.userDao.updateUserImageInfo(user);
        if(count > 0){
            return CommonResponse.successResponse("update userInfo success");
        }
        return CommonResponse.errorResponse("update userInfo error");
    }

    /**
     * 更新用户资料信息
     * @param request
     * @return
     */
    public CommonResponse updateUserInfo(EditUserInfoRequest request){
        User user = new User();
        user.setId(request.getUserId());
        BeanUtils.copyProperties(request, user);
        int count = this.userDao.updateUserInfo(user);
        if(count > 0){
            return CommonResponse.successResponse("update userInfo success");
        }
        return CommonResponse.errorResponse("update userInfo error");
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public CommonResponse login(User user){
        CommonResponse response = new CommonResponse(0,"success");
        user.setPassword(Parse2MD5.parseStrToMd5L32(user.getPassword()));
        User responseUser = this.userDao.login(user);
        if(null != responseUser){
            response.setMessage("login success");
            LoginResponse result = new LoginResponse();
            result.setUserId(responseUser.getId());
            result.setToken(responseUser.getToken());
            response.setResult(result);
        }else{
            response.setCode(1);
            response.setMessage("wrong username or password");
        }
        return response;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    public CommonResponse register(User user){
        CommonResponse response = new CommonResponse(0,"success");
        if(this.userDao.checkUsernameIsExist(user)){
            response.setCode(1);
            response.setMessage("username has been used");//用户名已被使用
            return response;
        }
        String passwordmd5 = Parse2MD5.parseStrToMd5L32(user.getPassword());
        String token = Parse2MD5.parseStrToMd5L32(user.getUsername() + user.getPassword());
        user.setPassword(passwordmd5);
        user.setToken(token);
        int userId = this.userDao.addUser(user);
        UserRegisterInfo userRegisterInfo = new UserRegisterInfo();
        userRegisterInfo.setUserId(userId);
        userRegisterInfo.setToken(token);
        response.setResult(userRegisterInfo);
        return response;
    }
}
