package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.VolunteerReq;
import com.xupt.edu.zwy.platformofhoping.model.Activity;
import com.xupt.edu.zwy.platformofhoping.model.Volunteer;
import com.xupt.edu.zwy.platformofhoping.service.IVolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-4-27
 * @Time 下午9:01
 */
@RestController
@RequestMapping("/volunteer")
@Slf4j
public class VolunteerController {
    @Resource
    private IVolunteerService volunteerService;

    @PostMapping("/delete")
    public CommonJsonResult deleteVolunteer(@RequestBody VolunteerReq volunteerReq) {
        log.info("into /volunteer/delete,volunteerReq:{}", volunteerReq);
        int result = volunteerService.deleteVolunteer(volunteerReq);
        log.info("out /volunteer/delete,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/add")
    public CommonJsonResult addVolunteer(@RequestBody VolunteerReq volunteerReq) {
        log.info("into /volunteer/add,volunteerReq:{}", volunteerReq);
        int result = volunteerService.addVolunteer(volunteerReq);
        log.info("out /volunteer/add,result:{}", result);
        return CommonJsonResult.success();
    }

    @GetMapping("/list")
    public CommonJsonResult<PageInfo<Volunteer>> getVolunteerList(VolunteerReq volunteerReq) {
        log.info("into /volunteer/list,volunteerReq:{}", volunteerReq);
        PageInfo<Volunteer> pageInfo = volunteerService.selectMyVolunteerList(volunteerReq);
        log.info("out /volunteer/list,pageInfo:{}", pageInfo);
        return CommonJsonResult.success(pageInfo);
    }

    @GetMapping("/activity")
    public CommonJsonResult<PageInfo<Activity>> getVolunteerActivityList(VolunteerReq volunteerReq) {
        log.info("into /volunteer/activity,volunteerReq:{}", volunteerReq);
        PageInfo<Activity> pageInfo = volunteerService.getVolunteerActivity(volunteerReq);
        log.info("out /volunteer/activity,pageInfo:{}", pageInfo);
        return CommonJsonResult.success(pageInfo);
    }
}
