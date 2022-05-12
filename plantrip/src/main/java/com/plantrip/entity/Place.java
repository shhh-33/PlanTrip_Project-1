package com.plantrip.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 여행 계획하기 할때 장소 선택하는 거 (아직 안만들어짐)
 */

@Entity
@Table(name = "place")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long placeId;

    @JoinColumn(name = "trip_id")
    @ManyToOne
    private Trip trip;

    // trip_id 별로 일자별 구분하여 insert
    @Column(name = "day_cnt")
    private Long dayCnt;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "hardness")
    private double hardness;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "detail_addr", length = 100)
    private String detailAddr;

    @Column(name = "memo", length = 300)
    private String memo;

    @Column(name = "expenses")
    private double expenses;
}
