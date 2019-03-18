package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import com.xupt.edu.zwy.platformofhoping.service.IOrganizerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizerServiceImplTest {
    @Resource
    private IOrganizerService organizerService;

    @Test
    public void isRightOrganizer() {
    }

    @Test
    public void registerOrganizer() {
        Organizer organizer=new Organizer();
        organizer.setOrganizerCity("西安市");
        organizer.setOrganizerName("西安市长安区聂河养老院");
        organizer.setOrganizerAddress("西安市长安区黄良街道办聂河中医医院");
        organizer.setOrganizerDescription("聂河养老院地处西安市长安区黄良街办街办聂家河村，占地面积十余亩，建筑面积3500余平方米，是一家医养康结合型的养老院；我们养老院一共有100张床位，设有双人间、三人间和多人间。");
        organizer.setOrganizerprincipal("陈永森");
        organizer.setOrganizerPhone("400-6090-872转15#");
        organizer.setPassword("niehe");
        int result = organizerService.registerOrganizer(organizer);
        System.out.println(result);
    }
}