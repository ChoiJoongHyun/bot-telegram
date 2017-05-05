package com.joonghyun.bot.conference.service;

import com.joonghyun.model.ConferenceReserve;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConferenceServiceTest {

    @Autowired
    private ConferenceService conferenceService;



    @Test
    public void allList() throws Exception {
        int size = conferenceService.allList("C601", "20170505").size();
        Assert.assertEquals(1, size);
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void reserve() throws Exception {
    }

}