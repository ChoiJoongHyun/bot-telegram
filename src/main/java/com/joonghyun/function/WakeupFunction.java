package com.joonghyun.function;

import com.joonghyun.anotation.Command;
import com.joonghyun.anotation.Function;
import com.joonghyun.helper.RedisHelper;
import com.joonghyun.model.request.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by joonghyun on 2017. 5. 3..
 */
@Function
public class WakeupFunction {

    @Autowired
    private RedisHelper redisHelper;

    public class Message {
        public static final String WAKEUP = "무엇을 도와 드릴까요?" +
                "\n'회의실' : 회의실 예약 및 취소 상황을 보여줍니다." +
                "\n'게임' : 간단한 게임 목록을 보여줍니다." +
                "\n'점심메뉴' : 오늘의 점심메뉴는!?" +
                "\n'서버현황' : 아이파킹 서버 상태를 보여줍니다." +
                "\n\n*필독*" +
                "\n30초간 입력이 없을시 저는 사라집니다." +
                "\n\n*예약어*" +
                "\n#잘가봇 : 봇을 종료시킨다.";



        public static final String GOODBYE = "다음에 또 만나요!";
    }

    @Command(msg = "#안녕봇", function = "wakeup")
    public String wakeup(MessageRequest messageRequest) {
        return Message.WAKEUP;
    }

    @Command(msg = "#잘가봇", function = "goodbye")
    public String goodbye(MessageRequest messageRequest) {
        redisHelper.delete(messageRequest.getRoomKey());
        return Message.GOODBYE; //iParkingBot
    }
}
