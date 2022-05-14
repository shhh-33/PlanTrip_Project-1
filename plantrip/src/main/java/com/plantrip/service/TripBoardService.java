package com.plantrip.service;

import com.plantrip.dto.TripBoardFormDto;
import com.plantrip.entity.TripBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripBoardService {

    public Long saveTripBoard(TripBoardFormDto tripBoardFormDto){

        return null;
    }


}
