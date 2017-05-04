package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.bot.conference.service.ConferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Function
public class ConferenceFunction {

    private static final Logger log = LoggerFactory.getLogger(ConferenceFunction.class);
    @Autowired
    private ConferenceService conferenceService;

    public class Message {
        public static final String CONFERENCE = "회의실과 날짜를 입력해 주세요" +
                "\n날짜를 입력하지 않으면 오늘날짜로 자동 입력 됩니다." +
                "\nex) C601, 20170505" +
                "\nex) C601";
        public static final String CONFERENCE_LIST = "예약을 원하시면 '예약' 을 입력하세요" +
                "취소를 원하시면 '취소' 를 입력하세요";
        public static final String CONFERENCE_RESERVE = "타임존, 이름, 회의내용을 아래와 같이 입력하세요" +
                "\nex) t1, 홍길동, R&D회의";
        public static final String CONFERENCE_RESERVE_SUCC = "예약 완료하였습니다";
        public static final String CONFERENCE_CANCEL = "타임존, 이름, 취소사유를 아래와 같이 입력하세요" +
                "\nex) t1, 홍길동, R&D회의";
        public static final String CONFERENCE_CANCEL_SUCC = "취소 완료하였습니다";
    }

    @Command(msg="회의실", function = "conference", parent = "wakeup")
    public String conference(String msg) {
        log.debug("conference start msg : {}", msg);


        return Message.CONFERENCE;
    }

    @Command(function = "conferenceList", parent = "conference")
    public String conferenceList(String msg) {
        log.debug("conferenceList start msg : {}", msg);


        //전체리스트 +
        return Message.CONFERENCE_LIST;
    }

    @Command(msg = "예약", function = "conferenceReserveList", parent = "conferenceList")
    public String conferenceReserveList(String msg) {
        log.debug("conferenceReserveList start msg : {}", msg);


        //예약 가능 목록 리스트 +
        return Message.CONFERENCE_RESERVE;
    }

    @Command(function = "reserve", parent = "conferenceReserveList")
    public String reserve(String msg) {
        log.debug("reserve start msg : {}", msg);
        //param ex ) t1, 이름, R&D회의

        return Message.CONFERENCE_RESERVE_SUCC;
    }


    @Command(msg = "취소", function = "conferenceCancelList", parent = "conferenceList")
    public String conferenceCancelList(String msg) {
        log.debug("conferenceCancelList start msg : {}", msg);
        //취소 가능 목록 리스트

        //예약 가능 목록 리스트 +
        return Message.CONFERENCE_CANCEL;
    }

    @Command(function = "cancel", parent = "conferenceCancelList")
    public String cancel(String msg) {
        log.debug("conferenceReserveList start msg : {}", msg);
        //param ex ) t1, 이름, 회의취소

        return Message.CONFERENCE_CANCEL_SUCC;
    }
}
