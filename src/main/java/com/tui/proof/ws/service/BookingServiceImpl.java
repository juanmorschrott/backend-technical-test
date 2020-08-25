package com.tui.proof.ws.service;

import com.tui.proof.ws.component.AvailabilityCacheComponent;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.repository.BookingRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
@Log4j2
@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private AvailabilityCacheComponent availabilityCacheComponent;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, AvailabilityCacheComponent availabilityCacheComponent) {
        this.bookingRepository = bookingRepository;
        this.availabilityCacheComponent = availabilityCacheComponent;
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<List<Booking>> findAll() throws InterruptedException {
        List<Booking> bookings = this.bookingRepository.findAll();
        Thread.sleep(5000);
        log.info("Bookings retrieved: {}", bookings);

        return CompletableFuture.completedFuture(bookings);
    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Booking> findById(Long id) throws InterruptedException {
        Booking booking = this.bookingRepository.findById(id);
        Thread.sleep(5000);

        if (booking != null) {
            log.info("Booking retrieved: {}", booking);
        } else {
            log.warn("Booking: {} not found", id);
        }

        return CompletableFuture.completedFuture(booking);
    }

    @Async("asyncExecutor")
    @Override
    public void create(Booking booking) throws InterruptedException {
        boolean isUnavailableFlight = false;

        for (Flight flight : booking.getFlights()) {
            if (!this.availabilityCacheComponent.isFlightAvailable(flight.getId())) {
                log.error("Flight: {} is not valid", flight.getId());
                isUnavailableFlight = true;
                break;
            }
        }

        if (!isUnavailableFlight) {
            Booking result = this.bookingRepository.create(booking);
            Thread.sleep(5000);
            log.info("Created Booking: {}", result);
        }

    }

    @Async("asyncExecutor")
    @Override
    public void delete(Long id) throws InterruptedException {
        if (this.bookingRepository.delete(id)) {
            Thread.sleep(5000);
            log.info("Booking: {} deleted", id);
        } else {
            log.warn("Booking: {} not found", id);
        }
    }

    @Async("asyncExecutor")
    @Override
    public void addFlightToBooking(Long id, Flight flight) throws InterruptedException {
        if (this.availabilityCacheComponent.isFlightAvailable(flight.getId())) {
            Booking booking = this.bookingRepository.addFlightToBooking(id, flight);
            Thread.sleep(5000);
            log.info("Flight added: {} on booking: {}", flight, booking);
        } else {
            log.error("Flight: {} is not valid", flight.getId());
        }

    }

    @Async("asyncExecutor")
    @Override
    public void deleteFlightFromBooking(Long bookingId, Long flightId) throws InterruptedException {
        Booking booking = this.bookingRepository.deleteFlightFromBooking(bookingId, flightId);
        Thread.sleep(5000);
        log.info("Flight {} deleted on booking: {}", flightId, booking);

    }

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<String> confirmBooking(Long id) throws InterruptedException {
        Booking booking = this.bookingRepository.findById(id);
        String result;

        if (booking.getFlights().isEmpty()) {
            log.warn("No flights to confirm on Booking: {}", id);
            result = "No flights to confirm on Booking:" + id;

        } else {
            Thread.sleep(5000);

            for (Flight flight : booking.getFlights()) {
                if (!this.availabilityCacheComponent.isFlightAvailable(flight.getId())) {
                    log.error("Flight: {} is not valid", flight.getId());
                    return CompletableFuture.completedFuture("Booking " + id + " with Flight: " + flight.getId() + " is not valid");
                } else {
                    log.info("Flight: {} is valid", flight.getId());
                }
            }
            log.info("Booking: {} confirmed", id);
            result = "Confirmed Booking: " + id;
        }

        return CompletableFuture.completedFuture(result);
    }
}
