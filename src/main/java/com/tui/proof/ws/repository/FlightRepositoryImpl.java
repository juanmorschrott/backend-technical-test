package com.tui.proof.ws.repository;

import com.tui.proof.ws.exception.NotFoundException;
import com.tui.proof.ws.model.Flight;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Repository
public class FlightRepositoryImpl implements FlightRepository {

    private List<Flight> flights;

    @Autowired
    public FlightRepositoryImpl() {
        this.flights = generateMockFlights();
    }

    @Override
    public List<Flight> findAll() {
        return this.flights;
    }

    @Override
    public Flight findById(Long id) {
        return this.flights.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst().orElseThrow(() -> new NotFoundException("Flight not found"));
    }

    @Override
    public void saveFlight(Long id, Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public void deleteFlight(Long id, Flight flight) {
        this.flights.removeIf(f -> f.getId().equals(id));
    }

    private List<Flight> generateMockFlights() {
        Flight f1 = new Flight();
        f1.setId(1L);
        f1.setCompany("Air Europa");
        f1.setDate(LocalDate.of(2020, 1, 1));
        f1.setHour(LocalTime.of(12, 30));
        f1.setFlightNumber("UX6025");
        f1.setMonetary(new BigDecimal("275.90"));

        Flight f2 = new Flight();
        f2.setId(2L);
        f2.setCompany("Iberia");
        f2.setDate(LocalDate.of(2020, 2, 29));
        f2.setHour(LocalTime.of(13, 30));
        f2.setFlightNumber("IB8410");
        f2.setMonetary(new BigDecimal("179.90"));

        Flight f3 = new Flight();
        f3.setId(3L);
        f3.setCompany("Tui");
        f3.setDate(LocalDate.of(2020, 6, 14));
        f3.setHour(LocalTime.of(14, 30));
        f3.setFlightNumber("6B156");
        f3.setMonetary(new BigDecimal("375.90"));

        Flight f4 = new Flight();
        f4.setId(4L);
        f4.setCompany("Luftansa");
        f4.setDate(LocalDate.of(2020, 1, 1));
        f4.setHour(LocalTime.of(12, 30));
        f4.setFlightNumber("LH2369");
        f4.setMonetary(new BigDecimal("575.90"));

        Flight f5 = new Flight();
        f5.setId(5L);
        f5.setCompany("Tui");
        f5.setDate(LocalDate.of(2020, 1, 1));
        f5.setHour(LocalTime.of(13, 30));
        f5.setFlightNumber("TB2277");
        f5.setMonetary(new BigDecimal("25.10"));

        Flight f6 = new Flight();
        f6.setId(6L);
        f6.setCompany("Luftansa");
        f6.setDate(LocalDate.of(2020, 1, 1));
        f6.setHour(LocalTime.of(14, 30));
        f6.setFlightNumber("LH1258");
        f6.setMonetary(new BigDecimal("375.90"));

        Flight f7 = new Flight();
        f7.setId(6L);
        f7.setCompany("Luftansa");
        f7.setDate(LocalDate.of(2020, 1, 1));
        f7.setHour(LocalTime.of(14, 30));
        f7.setFlightNumber("LH862");
        f7.setMonetary(new BigDecimal("375.90"));

        List<Flight> flights = Arrays.asList(f1, f2, f3, f4, f5, f6, f7);

        return flights;
    }

}
