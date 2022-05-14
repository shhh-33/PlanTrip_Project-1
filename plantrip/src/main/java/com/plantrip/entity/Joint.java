package com.plantrip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "joint")
public class Joint {    //결제로 여행 모집글 참여시 등록되는 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "joint_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToOne
    @JoinColumn(name = "trip_board_id")
    private TripBoard tripBoardId;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private JoinStatus jointStatus; // 여행 참여 참여중 or 취소

}
