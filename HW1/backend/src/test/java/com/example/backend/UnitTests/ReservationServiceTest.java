package com.example.backend.UnitTests;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.backend.model.Reservation;
import com.example.backend.repository.ReservationRepository;
import com.example.backend.service.ReservationService;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    @DisplayName("Test get all reservations")
    public void testGetAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        
        when(reservationRepository.findAll()).thenReturn(reservations);
        
        List<Reservation> result = reservationService.getAllReservations();
        
        assert(result.size() == 3);
    }

    @Test
    @DisplayName("Test get reservation by id")
    public void testGetReservationById() {
        Reservation reservation = new Reservation();
        reservation.setResId(1L);
        
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        
        Reservation result = reservationService.getReservationById(1L).get();
        
        assert(result.getResId() == 1L);
    }

    @Test
    @DisplayName("Test get reservations by bus connection id")
    public void testGetReservationsByBusConId() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        reservations.add(new Reservation());
        
        when(reservationRepository.findByBusConId(1L)).thenReturn(reservations);
        
        List<Reservation> result = reservationService.getReservationsByBusConId(1L);
        
        assert(result.size() == 3);
    }

    @Test
    @DisplayName("Test save reservation")
    public void testSaveReservation() {
        Reservation reservation = new Reservation();
        reservation.setResId(1L);
        
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        
        Reservation result = reservationService.saveReservation(reservation);
        
        assert(result.getResId() == 1L);
    }

    //ver do delete (provavelmente retirar)


    //fazer testes para os erros?

}
