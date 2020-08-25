package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;

import java.util.List;

public interface BookingRepository {

    List<Booking> findAll();

    Booking findById(Long id);

    Booking create(Booking booking);

    boolean delete(Long id);

    Booking addFlightToBooking(Long id, Flight flight);

    Booking deleteFlightFromBooking(Long bookingId, Long flightId);

}
