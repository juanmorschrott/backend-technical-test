package com.tui.proof.ws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Flight {

    private Long id;

    private String company;

    private String flightNumber;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern="HH:mm")
    private LocalTime hour;

    private BigDecimal monetary;

}
