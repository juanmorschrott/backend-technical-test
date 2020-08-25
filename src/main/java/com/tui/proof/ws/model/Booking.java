package com.tui.proof.ws.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class Booking {

    @NotNull
    private Long id;

    @NotNull
    private Holder holder;

    private List<Flight> flights = new ArrayList<>();

}
