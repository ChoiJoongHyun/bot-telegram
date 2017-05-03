package com.joonghyun.bot.conference.service;

import com.joonghyun.bot.conference.model.ConferenceVO;
import org.springframework.stereotype.Service;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Service
public class ConferenceService {

    /**
     * 해당 날짜의 특정 회의실 전체 목록
     * */
    public String allList(ConferenceVO conferenceVO) {

        return "allList";
    }

    /**
     * 회의실 취소
     * */
    public String cancel(ConferenceVO conferenceVO) {

        return "cancel";
    }

    /**
     * 회의실 예약
     * */
    public String reserve (ConferenceVO conferenceVO) {
        return "reserve";
    }


}
