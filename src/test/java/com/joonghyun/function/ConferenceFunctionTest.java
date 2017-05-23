package com.joonghyun.function;

import com.joonghyun.model.request.MessageRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by joonghyun on 2017. 5. 7..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConferenceFunctionTest {

    private static final Logger log = LoggerFactory.getLogger(ConferenceFunctionTest.class);

    private static final String ROOM_KEY = "999";

    @Autowired
    private ConferenceFunction conferenceFunction;

    @Test
    public void conference() throws Exception {

    }

    @Test
    public void conferenceList() throws Exception {
        String request = "C602,20170505";
        MessageRequest messageRequest = new MessageRequest(ROOM_KEY, request);
        String response = conferenceFunction.conferenceList(messageRequest);
        log.info("response : {}", response);
    }

    @Test
    public void conferenceReserveList() throws Exception {
    }

    @Test
    public void reserve() throws Exception {
    }

    @Test
    public void conferenceCancelList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

}