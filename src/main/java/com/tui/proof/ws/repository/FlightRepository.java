package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Flight;

import java.util.List;

public interface FlightRepository {

    List<Flight> findAll();

    Flight findById(Long id);

    void saveFlight(Long id, Flight flight);

    void deleteFlight(Long id, Flight flight);

}
