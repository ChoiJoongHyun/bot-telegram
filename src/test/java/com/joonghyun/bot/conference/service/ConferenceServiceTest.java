package com.joonghyun.bot.conference.service;

import com.joonghyun.bot.conference.model.ConferenceReserveDto;
import com.joonghyun.error.UserHandlerException;
import com.joonghyun.model.ConferenceReserve;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by joonghyun on 2017. 5. 5..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ConferenceServiceTest {

    @Autowired
    private ConferenceService conferenceService;

    @Test(expected = UserHandlerException.class)
    public void allList_존재하지않는_회의실() throws Exception {
        ConferenceReserveDto conferenceReserveDto = new ConferenceReserveDto();
        conferenceReserveDto.setZone("T1042");
        conferenceReserveDto.setDate("20170505");
        conferenceService.allList(conferenceReserveDto).size();
    }

    @Test
    public void allList() throws Exception {
        ConferenceReserveDto conferenceReserveDto = new ConferenceReserveDto();
        conferenceReserveDto.setZone("C602");
        conferenceReserveDto.setDate("20170505");
        int size = conferenceService.allList(conferenceReserveDto).size();
        Assert.assertEquals(3, size);
    }

    @Test
    public void cancel() throws Exception {
        ConferenceReserveDto conferenceReserveDto = new ConferenceReserveDto();
        conferenceReserveDto.setZone("C602");
        conferenceReserveDto.setDate("20170505");
        conferenceReserveDto.setTimeZone("T10");
        conferenceReserveDto.setCancelName("최중현");
        conferenceReserveDto.setCancelReason("취소");
        conferenceService.cancel(conferenceReserveDto);
    }

    @Test
    public void reserve() throws Exception {
        ConferenceReserveDto conferenceReserveDto = new ConferenceReserveDto();
        conferenceReserveDto.setZone("C602");
        conferenceReserveDto.setDate("20170505");
        conferenceReserveDto.setTimeZone("T13");
        conferenceReserveDto.setReserveName("최중현");
        conferenceReserveDto.setContent("연구소회의");
        conferenceService.reserve(conferenceReserveDto);
    }

}