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

    public enum ErrorCode implements Code {

        NO_EXIST_CONFERENCE("3001", "회의실이 존재하지 않습니다.")
        ,AlREADY_RESERVE("3002", "이미 예약이 되어 있습니다.")
        ,NO_EXIST_TIMEZONE("3003", "존재하지 않는 시간 입니다.")
        ,EMPTY_CONFERENCE("3004", "회의실이 비어 있습니다.")
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
     * 회의실 확인
     * */
    private Conference getConference(Conference.Zone zone) {
        Conference conference = conferenceRepository.findByZone(zone);
        if(conference == null) {
            throw new UserHandlerException(ErrorCode.NO_EXIST_CONFERENCE, zone.name());
        }
        return conference;
    }
    /**
     * 회의실
     * */
    private ConferenceReserve conferenceReserve(String date, Conference conference, ConferenceReserve.TimeZone timeZone) {
        return conferenceReserveRepository.findAllByDateAndConferenceAndTimeZone(date, conference, timeZone);
    }




    /**
     * 해당 날짜의 특정 회의실 전체 목록
     * String zone, String date
     * */
    public List<ConferenceReserve> allList(ConferenceReserveDto conferenceReserveDto) {
        List<ConferenceReserve> conferenceReserveList =
                conferenceReserveRepository.findAllByDateAndConferenceOrderByTimeZone(conferenceReserveDto.getDate(), getConference(conferenceReserveDto.getZone()));
        System.out.println(conferenceReserveList.size());
        return conferenceReserveList;
    }

    /**
     * 회의실 취소
     * */
    public ConferenceReserve cancel(ConferenceReserveDto conferenceReserveDto) {

        Conference conference = getConference(conferenceReserveDto.getZone());

        ConferenceReserve conferenceReserve = conferenceReserve(conferenceReserveDto.getDate(), conference, conferenceReserveDto.getTimeZone());
        if(conferenceReserve == null) {
            throw new UserHandlerException(ErrorCode.EMPTY_CONFERENCE);
        }
;
        conferenceReserve.setCancelName(conferenceReserveDto.getCancelName());
        conferenceReserve.setCancelReason(conferenceReserveDto.getCancelReason());
        conferenceReserve.setDelete(true);

        return conferenceReserveRepository.save(conferenceReserve);
    }

    /**
     * 회의실 예약
     * String date, String zone, String timeZone, String reserveName, String content
     * */
    public ConferenceReserve reserve (ConferenceReserveDto conferenceReserveDto) {

        Conference conference = getConference(conferenceReserveDto.getZone());

        ConferenceReserve conferenceReserve = conferenceReserve(conferenceReserveDto.getDate(), conference, conferenceReserveDto.getTimeZone());
        if(conferenceReserve != null) {
            throw new UserHandlerException(ErrorCode.AlREADY_RESERVE);
        }

        conferenceReserve = new ConferenceReserve();
        conferenceReserve.setDate(conferenceReserveDto.getDate());
        conferenceReserve.setConference(conference);
        conferenceReserve.setTimeZone(conferenceReserveDto.getTimeZone());
        conferenceReserve.setReserveName(conferenceReserveDto.getReserveName());
        conferenceReserve.setContent(conferenceReserveDto.getContent());

        return conferenceReserveRepository.save(conferenceReserve);
    }
}
