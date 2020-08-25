package com.tui.proof.ws.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class FlightDto {

    // IATA Code
    @NotNull
    @Size(max = 4)
    private String airportOrigin;

    // IATA Code
    @NotNull
    @Size(max = 4)
    private String airportDestination;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    @NotNull
    private PaxesDto paxesDto;

}
