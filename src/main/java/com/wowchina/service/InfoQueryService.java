package com.wowchina.service;

import com.wowchina.dao.IndustryDao;
import com.wowchina.dao.MajorDao;
import com.wowchina.domain.Industry;
import com.wowchina.domain.Major;
import com.wowchina.model.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangguisheng on 16/6/23.
 */
@Service
public class InfoQueryService {

    @Autowired
    private IndustryDao industryDao;
    @Autowired
    private MajorDao majorDao;

    /**
     * 查询所有Major信息
     * @return
     */
    public CommonResponse<List<Major>> queryAllMajor(){
        List<Major> majors = this.majorDao.queryAllMajor();
        CommonResponse<List<Major>> commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(majors);
        return commonResponse;
    }

    /**
     * 查询所有Industry信息
     * @return
     */
    public CommonResponse<List<Industry>> queryAllIndustry(){
        List<Industry> industrys = this.industryDao.queryAllIndustry();
        CommonResponse<List<Industry>> commonResponse = CommonResponse.successResponse();
        commonResponse.setResult(industrys);
        return commonResponse;
    }

}
