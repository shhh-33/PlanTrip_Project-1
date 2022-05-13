package com.plantrip.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "joint_trip")
public class JointTrip {    // 참여 완료된 정보 확인 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "joint_trip_id")
    private Long id;

    @OneToOne
    @Column(name = "joint_id")
    private Joint jointId;      //어떤 여행 모집글인지

    @OneToOne
    @Column(name = "trip_id")
    private Trip tripId;        //어떤 여행인지
}
