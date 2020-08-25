package com.tui.proof.ws.component;

import com.tui.proof.ws.model.Flight;

import java.util.List;

public interface AvailabilityCacheComponent {

    /**
     * Save a list of flights and count down until expiration time.
     *
     * @param flights
     */
    void cacheAvailabilities(List<Flight> flights);

    /**
     * Check if the flight passed by parameter is still available
     * Cost: O(n)
     *
     * @param flightId
     * @return boolean if the flight has expired
     */
    boolean isFlightAvailable(Long flightId);

}
