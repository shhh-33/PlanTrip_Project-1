package com.plantrip.dto;

import com.plantrip.entity.TripBoard;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TripBoardFormDto { //여행루트게시물 참여 데이터 정보 전달 DTO

    private Long id;

    private String TripBoardNm; //여행루트 게시물 참여 제목

    private String TripBoardContent;    // 내용

    private Integer party;  //여행루트 게시물 추가 참여 인원

}
