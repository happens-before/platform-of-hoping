package com.xupt.edu.zwy.platformofhoping.controller;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import com.xupt.edu.zwy.platformofhoping.service.IOrganizerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午8:26
 */
@RestController
@Slf4j
@RequestMapping("/organizer")
public class OrganizerController {
    @Resource
    private IOrganizerService organizerService;

    @GetMapping("/login")
    public CommonJsonResult organizerLogin(OrganizerReq organizerReq, HttpServletResponse response) {
        log.info("into /organizer/login,organizerReq:{}", organizerReq);
        if ((!organizerService.isRightInfo(organizerReq, response))) {
            return CommonJsonResult.fail(ReturnCodes.FAILD, "组织名或密码错误");
        }
        log.info("out /organizer/login");
        return CommonJsonResult.success();
    }

    @PostMapping("/register")
    public CommonJsonResult orgainzerRegister(@RequestBody Organizer organizer) {
        log.info("into /organizer/register,organizer:{}", organizer);
        int result = organizerService.registerOrganizer(organizer);
        log.info("out /organizer/register,result:{}", result);
        return CommonJsonResult.success();
    }

    @GetMapping("/info")
    public CommonJsonResult<Organizer> organizerInfo(OrganizerReq organizerReq) {
        log.info("into /organizer/info,organizer:{}", organizerReq);
        Organizer organizer = organizerService.selectOrganizerById(organizerReq);
        log.info("out /organizer/info,organizer:{}", organizer);
        return CommonJsonResult.success(organizer);
    }

    @GetMapping("/list")
    public CommonJsonResult<PageInfo<Organizer>> getOrganizerInfo(OrganizerReq organizerReq) {
        log.info("into /organizer/list,organizer:{}", organizerReq);
        PageInfo<Organizer> pageInfo = organizerService.selectOragnizerList(organizerReq);
        log.info("out /organizer/list,pageInfo:{}", pageInfo);
        return CommonJsonResult.success(pageInfo);
    }

    @PostMapping("/delete")
    public CommonJsonResult deleteOrganizer(@RequestBody OrganizerReq organizerReq) {
        log.info("into /organizer/delete,organizerReq:{}", organizerReq);
        int result = organizerService.deleteOrganizer(organizerReq);
        log.info("out /organizer/delete,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/update")
    public CommonJsonResult updateOrganizer(@RequestBody Organizer organizer) {
        log.info("into /organizer/update,organizer:{}", organizer);
        int result = organizerService.updateOrganizer(organizer);
        log.info("out /organizer/update,result:{}", result);
        return CommonJsonResult.success();
    }


}
