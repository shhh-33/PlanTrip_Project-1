package com.plantrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 여행 만들기
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateTripDto {
    private String startAt;
    private String endAt;
    private String region;
    private double totalExpenses;
}
