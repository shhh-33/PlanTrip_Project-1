package com.plantrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 여행만들기
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateTripDto {
    private String startAt; //시작날짜
    private String endAt; //마지막날짜
    private String region; //지역선택
    //private double totalExpenses;
}
