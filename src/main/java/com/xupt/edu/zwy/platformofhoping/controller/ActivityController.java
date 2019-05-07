package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityDetailInfo;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityListReq;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.enums.UserRoleEnum;
import com.xupt.edu.zwy.platformofhoping.model.Activity;
import com.xupt.edu.zwy.platformofhoping.service.IActivityService;
import com.xupt.edu.zwy.platformofhoping.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-18
 * @Time 下午5:12
 */
@RestController
@Slf4j
@RequestMapping("/activity")
public class ActivityController {
    @Resource
    private IActivityService activityService;

    @GetMapping("/list")
    public CommonJsonResult<PageInfo<Activity>> selectActivityList(ActivityListReq activityListReq) {
        log.info("into /activity/list,activityListReq:{}", activityListReq);
        int identity = Integer.parseInt(RequestUtil.getLoginUserStringIdentity());
        System.out.println(identity);
        System.out.println(UserRoleEnum.ADMIN.getRoleFlag());
        if (activityListReq.getFindAll() == 1 && identity == UserRoleEnum.ADMIN.getRoleFlag()) {
        } else if (identity == UserRoleEnum.ORGANIZER.getRoleFlag() && activityListReq.getFindAll() == 0) {
            activityListReq.setOrganizer(RequestUtil.getLoginUserName());
        } else if (identity == UserRoleEnum.ADMIN.getRoleFlag() && activityListReq.getFindAll() == 0) {
            activityListReq.setPromoter(RequestUtil.getLoginUserName());
        } else {
            throw new BusinessException(ReturnCodes.FAILD, "清注册信息");
        }
        PageInfo<Activity> pageInfo = activityService.selectActivityList(activityListReq);
        log.info("out activity/list,pageInfo:{}", pageInfo);
        return CommonJsonResult.success(pageInfo);
    }

    @PostMapping("/add")
    public CommonJsonResult addActivity(ActivityReq activityReq, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        log.info("into /activity/add,activityReq:{}", activityReq);
        int identity = Integer.parseInt(RequestUtil.getLoginUserStringIdentity());
        if (activityReq.getFindAll() == 1 && identity == UserRoleEnum.ADMIN.getRoleFlag()) {
        } else if (identity == UserRoleEnum.ORGANIZER.getRoleFlag() && activityReq.getFindAll() == 0) {
            activityReq.setOrganizer(RequestUtil.getLoginUserName());
        } else if (identity == UserRoleEnum.ADMIN.getRoleFlag() && activityReq.getFindAll() == 0) {
            activityReq.setPromoter(RequestUtil.getLoginUserName());
        } else if(identity==UserRoleEnum.USER.getRoleFlag() && Integer.parseInt(RequestUtil.getLoginUserStringMinister()) == 1 && activityReq.getFindAll() == 0){
            activityReq.setPromoter(RequestUtil.getLoginUserName());
        }
        else {
            throw new BusinessException(ReturnCodes.FAILD, "清注册信息或权限不足");
        }
        int result = activityService.addActivity(file, activityReq, request);
        log.info("out activity/add,result:{}", result);
        return CommonJsonResult.success();
    }

    @GetMapping("/detail")
    public CommonJsonResult<ActivityDetailInfo> getActivityDetail(@RequestParam(value = "activityId") String activityId) {
        log.info("into /activity/detail,activityId:{}", activityId);
        ActivityDetailInfo activityDetail = activityService.getActivityDetail(activityId);
        log.info("out activity/detail,activityDetail:{}", activityDetail);
        return CommonJsonResult.success(activityDetail);
    }

    @PostMapping("/update")
    public CommonJsonResult updateActivity(ActivityReq activityReq, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        log.info("into /activity/update,activityReq:{}", activityReq);
        int result = activityService.updateActivity(file, activityReq, request);
        log.info("out activity/update,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/delete")
    public CommonJsonResult deleteActivity(@RequestBody ActivityReq activityReq) {
        log.info("into /activity/delete,activityReq:{}", activityReq);
        int result = activityService.deleteActivity(activityReq.getActivityId());
        log.info("out activity/delete,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/publish")
    public CommonJsonResult publishActivity(@RequestBody ActivityReq activityReq) {
        log.info("into /activity/publish,activityReq:{}", activityReq);
        int result = activityService.publishActivity(activityReq);
        log.info("out activity/publish,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/end")
    public CommonJsonResult endActivity(@RequestBody ActivityReq activityReq) {
        log.info("into /activity/end,activityReq:{}", activityReq);
        int result = activityService.endActivity(activityReq);
        log.info("out activity/end,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/approval")
    public CommonJsonResult approvalActivity(@RequestBody ActivityReq activityReq) {
        log.info("into /activity/approval,activityReq:{}", activityReq);
        int result = activityService.approvalActivity(activityReq);
        log.info("out activity/approval,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/refuse")
    public CommonJsonResult refuseActivity(@RequestBody ActivityReq activityReq) {
        log.info("into /activity/refuse,activityReq:{}", activityReq);
        int result = activityService.refuseActivity(activityReq);
        log.info("out activity/refuse,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/summary")
    public CommonJsonResult summaryActivity(ActivityReq activityReq, @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        log.info("into /activity/summary,activityReq:{}", activityReq);
        if(file==null){
            return CommonJsonResult.fail(ReturnCodes.NULL_POINTER,"文件不能为空，请上传文件");
        }
        int result = activityService.summaryActivity(file, activityReq, request);
        log.info("out activity/summary,result:{}", result);
        return CommonJsonResult.success();
    }
}
