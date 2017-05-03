package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.domain.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Component
public class ConferenceFunction {

    @Autowired
    private ConferenceService conferenceService;

    @Command(value="회의실", function = "conference", parent = "wakeup")
    public String conference() {

        return "회의실";
    }

    @Command(value="예약", function = "reserve", parent = "conference")
    public String reserve() {

        return "예약";
    }

    @Command(value="취소", function = "cancel", parent = "conference")
    public String cancel() {

        return "취소";
    }
}
