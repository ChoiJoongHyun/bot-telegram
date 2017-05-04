package com.joonghyun.dispatch;

import com.joonghyun.function.ConferenceFunction;
import com.joonghyun.function.WakeupFunction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by joonghyun on 2017. 5. 4..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageDispatchTest {

    @Autowired
    private MessageDispatch messageDispatch;

    static final Long ROOM_KEY = 999L;


    @Test
    public void message_최초시작() throws Exception {
        String result = messageDispatch.message(ROOM_KEY, "#wakeup!");
        Assert.assertEquals(WakeupFunction.Message.WAKEUP, result);
    }

    @Test
    public void message_회의실() throws Exception {
        String result = messageDispatch.message(ROOM_KEY, "회의실");
        Assert.assertEquals(ConferenceFunction.Message.CONFERENCE, result);
    }

}