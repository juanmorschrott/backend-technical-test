package com.tui.proof.ws.service;

import com.tui.proof.ws.component.AvailabilityCacheComponent;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.repository.FlightRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private FlightRepository flightRepository;

    private AvailabilityCacheComponent availabilityCacheComponent;

    @Autowired
    public AvailabilityServiceImpl(FlightRepository flightRepository, AvailabilityCacheComponent availabilityCacheComponent) {
        this.flightRepository = flightRepository;
        this.availabilityCacheComponent = availabilityCacheComponent;
    }

    @Async
    @Override
    public CompletableFuture<List<Flight>> findAll() {
        List<Flight> flights = this.flightRepository.findAll();

        if (flights != null) {
            availabilityCacheComponent.cacheAvailabilities(flights);
        }

        return CompletableFuture.completedFuture(flights);
    }

}
