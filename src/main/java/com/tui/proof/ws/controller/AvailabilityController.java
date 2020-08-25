package com.tui.proof.ws.controller;

import com.tui.proof.ws.dto.FlightDto;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.service.AvailabilityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("/availabilities")
public class AvailabilityController {

    private AvailabilityService availabilityService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    public ResponseEntity<List<Flight>> checkAvailabilityOfFlights(@RequestBody FlightDto flightDto) throws ExecutionException, InterruptedException {
        log.info("[request] -> POST /availabilities", flightDto);

        CompletableFuture<List<Flight>> flights = availabilityService.findAll();

        return new ResponseEntity(flights.get(), HttpStatus.OK);
    }

}
