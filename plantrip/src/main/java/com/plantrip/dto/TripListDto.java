package com.plantrip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 여행 리스트 출력
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TripListDto {
    private Long tripId;
    private String startAt;
    private String endAt;
    private String region;
    private double totalExpenses;
    private long dayCount;
    private long attendeeCount;
}
