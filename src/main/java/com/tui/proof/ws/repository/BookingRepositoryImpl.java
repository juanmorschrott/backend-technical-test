package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Holder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Repository
public class BookingRepositoryImpl implements BookingRepository {

    private List<Booking> bookings;

    public BookingRepositoryImpl() {
        this.bookings = generateBookings();
    }

    @Override
    public List<Booking> findAll() {
        return this.bookings;
    }

    @Override
    public Booking findById(Long id) {
        return this.bookings.stream()
                .filter(booking -> booking.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Booking create(Booking booking) {
        this.bookings.add(booking);

        return booking;
    }

    @Override
    public boolean delete(Long id) {
        return this.bookings.removeIf(booking -> booking.getId().equals(id));
    }

    @Override
    public Booking addFlightToBooking(Long id, Flight flight) {
        Booking found = this.bookings.stream()
                    .filter(b -> b.getId().equals(id))
                    .findFirst()
                    .orElse(null);

        if (found != null) found.getFlights().add(flight);

        return found;
    }

    @Override
    public Booking deleteFlightFromBooking(Long bookingId, Long flightId) {
        Booking found = this.bookings.stream()
                .filter(b -> b.getId().equals(bookingId))
                .findFirst()
                .orElse(null);

        if (found != null) found.getFlights().removeIf(flight -> flight.getId().equals(flightId));

        return found;
    }

    private List<Booking> generateBookings() {
        Holder h1 = new Holder();
        h1.setAddress("Calle Alemania, 31");
        h1.setCountry("ES");
        h1.setEmail("juanmorschrott@outlook.com");
        h1.setName("Juan Andres");
        h1.setLastName("Moreno");
        h1.setPhones(Arrays.asList("658068203", "654321987"));
        h1.setPostalCode("07007");

        Flight f1 = new Flight();
        f1.setId(1L);
        f1.setCompany("Air Europa");
        f1.setDate(LocalDate.of(2020, 1, 1));
        f1.setHour(LocalTime.of(12, 30));
        f1.setFlightNumber("UX6025");
        f1.setMonetary(new BigDecimal("275.90"));

        Booking b1 = new Booking();
        b1.setId(1L);
        b1.setHolder(h1);
        b1.getFlights().add(f1);

        Holder h2 = new Holder();
        h2.setAddress("Calle Argentina, 121");
        h2.setCountry("AR");
        h2.setEmail("reinaldo@gmail.com");
        h2.setName("Reinaldo");
        h2.setLastName("Gonzalez");
        h2.setPhones(Arrays.asList("639258147"));
        h2.setPostalCode("0846");

        Flight f2 = new Flight();
        f2.setId(2L);
        f2.setCompany("Iberia");
        f2.setDate(LocalDate.of(2020, 2, 29));
        f2.setHour(LocalTime.of(13, 30));
        f2.setFlightNumber("IB8410");
        f2.setMonetary(new BigDecimal("179.90"));

        Booking b2 = new Booking();
        b2.setId(2L);
        b2.setHolder(h2);
        b2.getFlights().add(f2);

        List<Booking> bs = new ArrayList<>();
        bs.add(b1);
        bs.add(b2);

        return bs;
    }

}
