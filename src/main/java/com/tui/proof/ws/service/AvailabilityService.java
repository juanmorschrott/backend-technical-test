package com.tui.proof.ws.service;

import com.tui.proof.ws.model.Flight;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AvailabilityService {

    CompletableFuture<List<Flight>> findAll();

}
