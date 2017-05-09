package com.joonghyun.bot.conference.service;

import com.joonghyun.bot.conference.model.ConferenceReserveDto;
import com.joonghyun.bot.conference.repository.ConferenceRepository;
import com.joonghyun.bot.conference.repository.ConferenceReserveRepository;
import com.joonghyun.error.Code;
import com.joonghyun.error.UserHandlerException;
import com.joonghyun.model.Conference;
import com.joonghyun.model.ConferenceReserve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Service
public class ConferenceService {

    private static final Logger log = LoggerFactory.getLogger(ConferenceService.class);

    @Autowired
    private ConferenceReserveRepository conferenceReserveRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    private enum ErrorCode implements Code {

        NO_CONFERENCE_ZONE("3001", "회의실이 존재하지 않습니다.")
        ,AlREADY_RESERVE("3002", "이미 예약이 되어 있습니다.")
        ,NO_EXIST_TIMEZONE("3003", "존재하지 않는 시간 입니다.")
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
    /**
     * 타임존
     * */
    private ConferenceReserve.TimeZone getTimeZone(String timeZone) {
        try {
            return ConferenceReserve.TimeZone.valueOf(timeZone);
        } catch (IllegalArgumentException e) {
            throw new UserHandlerException(ErrorCode.NO_EXIST_TIMEZONE);
        }
    }
    /**
     * 회의실 확인
     * */
    private Conference getConference(String zone) {
        try {
            Conference conference = conferenceRepository.findByZone(Conference.Zone.valueOf(zone));
            if(conference == null) {
                throw new UserHandlerException(ErrorCode.NO_CONFERENCE_ZONE, zone);
            }
            return conference;
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UserHandlerException(ErrorCode.NO_CONFERENCE_ZONE, zone);
        }
    }
    /**
     * 회의실
     * */
    private ConferenceReserve conferenceReserve(String date, Conference conference, ConferenceReserve.TimeZone timeZone) {
        return conferenceReserveRepository.findAllByDateAndConferenceAndTimeZone(date, conference, timeZone);
    }




    /**
     * 해당 날짜의 특정 회의실 전체 목록
     * */
    public List<ConferenceReserve> allList(String zone, String date) {
        List<ConferenceReserve> conferenceReserveList = conferenceReserveRepository.findAllByDateAndConferenceOrderByTimeZone(date, getConference(zone));
        return conferenceReserveList;
    }

    /**
     * 회의실 취소
     * */
    public Boolean cancel(ConferenceReserveDto conferenceReserveDto) {

        return true;
    }

    /**
     * 회의실 예약
     * */
    public ConferenceReserve reserve (String date, String zone, String timeZone, String reserveName, String content ) {

        Conference conference = getConference(zone);
        ConferenceReserve.TimeZone cTimeZone = getTimeZone(timeZone);

        ConferenceReserve conferenceReserve = conferenceReserve(date, conference, cTimeZone);
        if(conferenceReserve != null) {
            throw new UserHandlerException(ErrorCode.AlREADY_RESERVE);
        }

        conferenceReserve = new ConferenceReserve();
        conferenceReserve.setDate(date);
        conferenceReserve.setConference(conference);
        conferenceReserve.setTimeZone(cTimeZone);
        conferenceReserve.setReserveName(reserveName);
        conferenceReserve.setContent(content);

        return conferenceReserveRepository.save(conferenceReserve);
    }
}
