package toyproject.controller.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserQuestionDto {
    private String talkId;
    private String userId;
    private String userNick;
    private String talkTitle;
    private String talkTypeCode;
    private String talkCont;
    private String talkCheck;
    private int viewCount;
    private LocalDate regisDate;
    private boolean isReplied;
}