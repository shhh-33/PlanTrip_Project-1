package com.plantrip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "trip_board")
public class TripBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trip_board_id")
    private Long id;    //여행 참여 게시글 아이디

    @OneToOne   //여행루트 하나는 하나의 여행 참여 게시물을 가능
    @JoinColumn(name = "trip_id")
    private Trip tripId;

    @ManyToOne(fetch = FetchType.LAZY)  // 참석자는 여러개의 여행 참여 게시물 참여 가능
    @JoinColumn(name = "attend_id")
    private User userId;

    private String title;   //여행 참여 게시글 제목
    private String content; // 여행 참여 게시글 내용
    private int party;  // 참여인원(현재 참여중 인원 제외)

    @Enumerated(EnumType.STRING)
    private TripBoardStatus status; // 모집중 및 모집완료 상태
}
