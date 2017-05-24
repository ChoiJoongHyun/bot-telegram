package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.bot.conference.model.ConferenceReserveDto;
import com.joonghyun.bot.conference.service.ConferenceService;
import com.joonghyun.error.GeneralCode;
import com.joonghyun.error.UserHandlerException;
import com.joonghyun.helper.RedisHelper;
import com.joonghyun.model.ConferenceReserve;
import com.joonghyun.model.converstation.ConversationInfo;
import com.joonghyun.model.request.MessageRequest;
import com.joonghyun.utils.ConversationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Function
public class ConferenceFunction {

    private static final Logger log = LoggerFactory.getLogger(ConferenceFunction.class);

    @Autowired
    private RedisHelper redisHelper;

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
                "\nex) t9, 최중현, R&D회의";
        public static final String CONFERENCE_RESERVE_SUCC = "예약 완료하였습니다";
        public static final String CONFERENCE_CANCEL = "타임존, 이름, 취소사유를 아래와 같이 입력하세요" +
                "\nex) t9, 최중현, 회의변경";
        public static final String CONFERENCE_CANCEL_SUCC = "취소 완료하였습니다";
    }

    @Command(msg="회의실", function = "conference", parent = "wakeup")
    public String conference(MessageRequest messageRequest) {
        log.debug("conference start messageRequest : {}", messageRequest.toString());
        return Message.CONFERENCE;
    }

    @Command(function = "conferenceList", parent = "conference")
    public String conferenceList(MessageRequest messageRequest) {
        log.debug("conferenceList start messageRequest : {}", messageRequest.toString());
        ConferenceReserveDto conferenceReserveDto = getConferenceReserveDto(messageRequest.getMsg());

        List<ConferenceReserve> conferenceReserveList = conferenceService.allList(conferenceReserveDto);

        final StringBuilder sb = new StringBuilder();
        for(ConferenceReserve.TimeZone d : ConferenceReserve.TimeZone.values()) {
            if(conferenceReserveList.isEmpty()) {
                sb.append("[").append(d.name()).append("] ").append(d.getDescript()).append(":").append("\n");
            } else {
                for(ConferenceReserve conferenceReserve : conferenceReserveList) {
                    if(d.name().equals(conferenceReserve.getTimeZone().name())) {
                        sb.append(conferenceReserve.toShortString()).append("\n");
                        break;
                    } else {
                        sb.append("[").append(d.name()).append("] ").append(d.getDescript()).append(":").append("\n");
                        break;
                    }
                }
            }
        }
        return "날짜 : " + conferenceReserveDto.getDate() + "\n" + sb.toString() + Message.CONFERENCE_LIST;
    }

    @Command(msg = "예약", function = "conferenceReserveList", parent = "conferenceList")
    public String conferenceReserveList(MessageRequest messageRequest) {
        log.debug("conferenceReserveList start messageRequest : {}", messageRequest.toString());
        return Message.CONFERENCE_RESERVE;
    }

    @Command(function = "reserve", parent = "conferenceReserveList")
    public String reserve(MessageRequest messageRequest) {
        log.debug("reserve start messageRequest : {}", messageRequest.toString());
        //param ex ) t1, 이름, R&D회의
        String beforeValue = redisHelper.getIndex(messageRequest.getRoomKey(), 2);  // {\"function\":\"conferenceList\",\"msg\":\"C601, 20170505\"}
        ConversationInfo conversationInfo = ConversationUtils.stringToObject(beforeValue);

        ConferenceReserveDto conferenceReserveDto = getConferenceReserveDto(conversationInfo.getMsg());

        String msgs[] = messageRequest.getMsg()
                .replaceAll(" ","")
                .split(",");
        if(msgs.length != 3 ) {
            throw new UserHandlerException(GeneralCode.NO_EXIST_COMMAND);
        }

        conferenceReserveDto.setTimeZone(msgs[0]);
        conferenceReserveDto.setReserveName(msgs[1]);
        conferenceReserveDto.setContent(msgs[2]);

        ConferenceReserve conferenceReserve = conferenceService.reserve(conferenceReserveDto);

        return conferenceReserve.getTimeZone().getDescript()
                + " " + conferenceReserve.getReserveName()
                + "\n" + Message.CONFERENCE_RESERVE_SUCC;
    }

    @Command(msg = "취소", function = "conferenceCancelList", parent = "conferenceList")
    public String conferenceCancelList(MessageRequest messageRequest) {
        log.debug("conferenceCancelList start messageRequest : {}", messageRequest.toString());
        //취소 가능 목록 리스트

        //예약 가능 목록 리스트 +
        return Message.CONFERENCE_CANCEL;
    }

    @Command(function = "cancel", parent = "conferenceCancelList")
    public String cancel(MessageRequest messageRequest) {
        log.debug("conferenceReserveList start messageRequest : {}", messageRequest.toString());

        String beforeValue = redisHelper.getIndex(messageRequest.getRoomKey(), 2);  // {\"function\":\"conferenceList\",\"msg\":\"C601, 20170505\"}
        ConversationInfo conversationInfo = ConversationUtils.stringToObject(beforeValue);

        ConferenceReserveDto conferenceReserveDto = getConferenceReserveDto(conversationInfo.getMsg());

        String msgs[] = messageRequest.getMsg()
                .replaceAll(" ","")
                .split(",");
        if(msgs.length != 3 ) {
            throw new UserHandlerException(GeneralCode.NO_EXIST_COMMAND);
        }

        conferenceReserveDto.setTimeZone(msgs[0]);
        conferenceReserveDto.setReserveName(msgs[1]);
        conferenceReserveDto.setContent(msgs[2]);

        ConferenceReserve conferenceReserve = conferenceService.cancel(conferenceReserveDto);

        return conferenceReserve.getTimeZone().getDescript()
                + " " + conferenceReserve.getReserveName()
                + "\n" + Message.CONFERENCE_CANCEL_SUCC;
    }

    private ConferenceReserveDto getConferenceReserveDto(String firstMsg) {
        String msgs[] = firstMsg
                .replaceAll(" ","")
                .split(",");
        if(msgs.length > 2) {
            throw new UserHandlerException(GeneralCode.NO_EXIST_COMMAND);
        }

        ConferenceReserveDto conferenceReserveDto = new ConferenceReserveDto();
        conferenceReserveDto.setZone(msgs[0]);
        if(msgs.length == 2) {
            conferenceReserveDto.setDate(msgs[1]);
        }
        return conferenceReserveDto;
    }
}
