package com.example.backend.controller;



import com.example.backend.dto.BookingDTO;
import com.example.backend.entity.Booking;
import com.example.backend.service.impl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            Booking booking = bookingService.createBooking(bookingDTO);

            Map<String, Object> response = new HashMap<>();
            response.put("code", "00");
            response.put("message", "Booking created successfully");
            response.put("data", booking);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("code", "99");
            errorResponse.put("message", "Failed to create booking: " + e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<List<Booking>> getBookingsByCustomerEmail(@PathVariable String email) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomerEmail(email));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Booking>> getBookingsByMovieId(@PathVariable Long movieId) {
        return ResponseEntity.ok(bookingService.getBookingsByMovieId(movieId));
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Booking>> getBookingsByTheaterId(@PathVariable Long theaterId) {
        return ResponseEntity.ok(bookingService.getBookingsByTheaterId(theaterId));
    }
}