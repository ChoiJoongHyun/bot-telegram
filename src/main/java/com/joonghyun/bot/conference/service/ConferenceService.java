package com.joonghyun.bot.conference.service;

import com.joonghyun.bot.conference.model.ConferenceVO;
import com.joonghyun.bot.conference.repository.ConferenceRepository;
import com.joonghyun.bot.conference.repository.ConferenceReserveRepository;
import com.joonghyun.error.Code;
import com.joonghyun.error.GeneralCode;
import com.joonghyun.error.UserHandlerException;
import com.joonghyun.model.Conference;
import com.joonghyun.model.ConferenceReserve;
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

    /**
     * 해당 날짜의 특정 회의실 전체 목록
     * */
    public List<ConferenceReserve> allList(String zone, String date) {
        Conference conference = conferenceRepository.findByZone(Conference.Zone.valueOf(zone));
        if(conference == null) {
            throw new UserHandlerException(ErrorCode.NO_CONFERENCE_ZONE);
        }

        List<ConferenceReserve> resultList = conferenceReserveRepository.findAllByDateAndConferenceOrderByTimeZone(date, conference);

        return resultList;
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
