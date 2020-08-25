package com.tui.proof.ws.component;

import com.tui.proof.ws.model.Flight;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Component
public class AvailabilityCacheComponentImpl implements AvailabilityCacheComponent {

    @Value("${availabilities.expiration.time}")
    @Min(1)
    private int availabilitiesExpirationTime;

    private List<Flight> cachedFlights;

    private LocalDateTime expireTime;

    @Override
    public void cacheAvailabilities(List<Flight> flights) {
        if ((cachedFlights == null && expireTime == null) || (expireTime != null && LocalDateTime.now().isAfter(this.expireTime))) {
            cachedFlights = flights;
            expireTime = LocalDateTime.now().plusMinutes(availabilitiesExpirationTime);
            log.info("Flights cached: {} with expire time: {}", flights, expireTime);
        }
    }

    @Override
    public boolean isFlightAvailable(Long flightId) {
        return this.cachedFlights != null &&
                this.cachedFlights.stream().anyMatch(flight -> flight.getId().equals(flightId)) &&
                this.expireTime != null &&
                LocalDateTime.now().isBefore(this.expireTime);
    }

}
