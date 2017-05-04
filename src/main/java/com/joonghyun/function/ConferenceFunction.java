package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.bot.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Function
public class ConferenceFunction {

    @Autowired
    private ConferenceService conferenceService;

    @Command(value="회의실", function = "conference", parent = "wakeup")
    public String conference(String msg) {

        return "회의실과 날짜를 입력해 주세요" +
                "\n날짜를 입력하지 않으면 오늘날짜로 자동 입력 됩니다." +
                "\nex) C601, 20170505 " +
                "\nex) C601";
    }

    @Command(function = "conferenceList", parent = "conference")
    public String conferenceList(String msg) {

        return "conferenceList 예약, 취소";
    }

    @Command(value = "예약", function = "conferenceReserveList", parent = "conferenceList")
    public String conferenceReserveList(String msg) {
        //예약 가능 목록 리스트

        return "예약 가능 리스트" +
                "\n ex) t1, 이름, R&D회의";
    }

    @Command(function = "reserve", parent = "conferenceReserveList")
    public String reserve(String msg) {
        //param ex ) t1, 이름, R&D회의

        return "예약 완료하였습니다";
    }


    @Command(value = "취소", function = "conferenceCancelList", parent = "conferenceList")
    public String conferenceCancelList(String msg) {
        //취소 가능 목록 리스트

        return "취소 목록 리스트, " +
                "\n t1, 이름, 회의취소";
    }

    @Command(function = "cancel", parent = "conferenceCancelList")
    public String cancel(String msg) {
        //param ex ) t1, 이름, 회의취소

        return "취소 완료하였습니다";
    }
}
