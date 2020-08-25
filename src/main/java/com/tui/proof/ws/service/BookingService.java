package com.tui.proof.ws.service;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BookingService {

    CompletableFuture<List<Booking>> findAll() throws InterruptedException;

    CompletableFuture<Booking> findById(Long id) throws InterruptedException;

    void create(Booking booking) throws InterruptedException;

    void delete(Long id) throws InterruptedException;

    void addFlightToBooking(Long id, Flight flight) throws InterruptedException;

    void deleteFlightFromBooking(Long bookingId, Long flightId) throws InterruptedException;

    CompletableFuture<String> confirmBooking(Long id) throws InterruptedException;

}
