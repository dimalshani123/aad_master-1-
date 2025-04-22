package com.example.backend.service.impl;


import com.example.backend.dto.BookingDTO;
import com.example.backend.entity.Booking;
import com.example.backend.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepository;

    @Transactional
    public Booking createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setMovieId(bookingDTO.getMovieId());
        booking.setMovieName(bookingDTO.getMovieName());
        booking.setTheaterId(bookingDTO.getTheaterId());
        booking.setTheaterName(bookingDTO.getTheaterName());
        booking.setCustomerEmail(bookingDTO.getCustomerEmail());
        booking.setSeats(bookingDTO.getSeats());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByCustomerEmail(String email) {
        return bookingRepository.findByCustomerEmail(email);
    }

    public List<Booking> getBookingsByMovieId(Long movieId) {
        return bookingRepository.findByMovieId(movieId);
    }

    public List<Booking> getBookingsByTheaterId(Long theaterId) {
        return bookingRepository.findByTheaterId(theaterId);
    }
}