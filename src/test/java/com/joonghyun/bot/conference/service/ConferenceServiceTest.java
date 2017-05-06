package com.joonghyun.bot.conference.service;

import com.joonghyun.error.UserHandlerException;
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

    @Test(expected = UserHandlerException.class)
    public void allList_존재하지않는_회의실() throws Exception {
        conferenceService.allList("T1042", "20170505").size();
    }

    @Test
    public void allList() throws Exception {
        int size = conferenceService.allList("C602", "20170505").size();
        Assert.assertEquals(3, size);
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void reserve() throws Exception {
    }

}