package com.joonghyun.bot.conference.service;

import com.joonghyun.bot.conference.model.ConferenceVO;
import com.joonghyun.bot.conference.repository.ConferenceRepository;
import com.joonghyun.bot.conference.repository.ConferenceReserveRepository;
import com.joonghyun.error.Code;
import com.joonghyun.error.GeneralCode;
import com.joonghyun.error.UserHandlerException;
import com.joonghyun.model.Conference;
import com.joonghyun.model.ConferenceReserve;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joonghyun on 2017. 5. 3..
 * generatedAlias0 where generatedAlias0.date=:param0
 */
@Service
public class ConferenceService {

    @Autowired
    private ConferenceReserveRepository conferenceReserveRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    private enum ErrorCode implements Code {

        NO_CONFERENCE_ZONE("3001", "회의실이 존재하지 않습니다.")
        ;

        private String code;
        private String message;

        @Override
        public String getCode() {
            return code;
        }

        @Override
        public String getMessage() {
            return message;
        }

        ErrorCode(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    private Conference getConference(String zone) {
        try {
            Conference conference = conferenceRepository.findByZone(Conference.Zone.valueOf(zone));
            if(conference == null) {
                throw new UserHandlerException(ErrorCode.NO_CONFERENCE_ZONE);
            }
            return conference;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserHandlerException(ErrorCode.NO_CONFERENCE_ZONE);
        }
    }

    /**
     * 해당 날짜의 특정 회의실 전체 목록
     * */
    public List<ConferenceReserve> allList(String zone, String date) {
        List<ConferenceReserve> resultList = conferenceReserveRepository.findAllByDateAndConferenceOrderByTimeZone(date, getConference(zone));

        for(ConferenceReserve.TimeZone d : ConferenceReserve.TimeZone.values()) {
            System.out.println();
        }

        return resultList;
    }

    /**
     * 회의실 취소
     * */
    public Boolean cancel(ConferenceVO conferenceVO) {

        return true;
    }

    /**
     * 회의실 예약
     * */
    public Boolean reserve (ConferenceVO conferenceVO) {
        return true;
    }


}
