package com.joonghyun.dispatch;

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

    @Test
    public void message() throws Exception {
        messageDispatch.message(999L, "회의실");
    }

}