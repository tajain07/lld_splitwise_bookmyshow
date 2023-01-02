package com.lld.bms.dtos;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

import com.lld.bms.models.Show;
import com.lld.bms.models.ShowSeat;

@Data
public class CreateBookingDTO {
    @NonNull
    private Show show;
    @NonNull
    private List<ShowSeat> showSeats;
    @NonNull
    private Long customerId;
}
