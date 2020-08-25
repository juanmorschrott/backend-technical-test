package com.tui.proof.ws.controller;

import com.tui.proof.ws.component.AvailabilityCacheComponent;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Log4j2
@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private BookingService bookingService;

    private AvailabilityCacheComponent availabilityCacheComponent;

    @Autowired
    public BookingController(BookingService bookingService, AvailabilityCacheComponent availabilityCacheComponent) {
        this.bookingService = bookingService;
        this.availabilityCacheComponent = availabilityCacheComponent;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() throws ExecutionException, InterruptedException {
        log.info("[request] -> GET /bookings");

        CompletableFuture<List<Booking>> bookings = this.bookingService.findAll();

        return new ResponseEntity(bookings.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> bookingDetails(@PathVariable("id") Long id) throws ExecutionException, InterruptedException {
        log.info("[request] -> GET /bookings/{}", id);

        CompletableFuture<Booking> booking = this.bookingService.findById(id);
        Booking result = booking.get();

        if (result != null) {
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity("Booking not found", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping
    public ResponseEntity<String> createBooking(@Valid @RequestBody Booking booking) throws InterruptedException {
        log.info("[request] -> PUT /bookings {}", booking);

        this.bookingService.create(booking);

        return new ResponseEntity("Booking create order received", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") Long id) throws InterruptedException {
        log.info("[request] -> DELETE /bookings/{}", id);

        this.bookingService.delete(id);

        return new ResponseEntity("Booking delete order received", HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> addFlightToBooking(@PathVariable("id") Long id, @RequestBody Flight flight) throws InterruptedException {
        log.info("[request] -> POST /bookings/{} {}", id, flight);

        this.bookingService.addFlightToBooking(id, flight);

        return new ResponseEntity("Add flight to booking order received", HttpStatus.OK);
    }

    @DeleteMapping("/{bookingId}/flights/{flightId}")
    public ResponseEntity<String> deleteFlightFromBooking(@PathVariable("bookingId") Long bookingId,
                                                  @PathVariable("flightId") Long flightId) throws InterruptedException {
        log.info("[request] -> DELETE /bookings/{}/flights/{}", bookingId, flightId);

        this.bookingService.deleteFlightFromBooking(bookingId, flightId);

        return new ResponseEntity("Delete flight from booking order received", HttpStatus.OK);
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<String> confirmBooking(@PathVariable("id") Long id) throws InterruptedException, ExecutionException {
        log.info("[request] -> GET /bookings/{}", id);

        CompletableFuture<String> future = this.bookingService.confirmBooking(id);

        return new ResponseEntity<>(future.get(), HttpStatus.OK);
    }

}
