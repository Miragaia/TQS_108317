package com.example.backend.serviceTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
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

    private Reservation reservation1;
    private Reservation reservation2;
    private Reservation reservation3;

    @BeforeEach
    void setUp() {
        reservation1 = new Reservation();
        reservation1.setResId(1L);
        reservation1.setBusConId(1L);
        reservation1.setPassengerName("Vinicius Junior");
        reservation1.setAddress("64, Rua Madrid");
        reservation1.setCity("Madrid");
        reservation1.setCountry("Espanha");
        reservation1.setZipCode("12345");
        reservation1.setCreditCardNumber("1234567890123456");
        reservation1.setCardHolderName("Vinicius Junior");
        reservation1.setCardExpirationMonth("01");
        reservation1.setCardExpirationYear("2023");

        reservation2 = new Reservation();
        reservation2.setResId(2L);
        reservation2.setBusConId(2L);
        reservation2.setPassengerName("Rodrygo Goes");
        reservation2.setAddress("64, Rua Malaga");
        reservation2.setCity("Malaga");
        reservation2.setCountry("Espanha");
        reservation2.setZipCode("12345");
        reservation2.setCreditCardNumber("1234567890123456");
        reservation2.setCardHolderName("Rodrygo Goes");
        reservation2.setCardExpirationMonth("05");
        reservation2.setCardExpirationYear("2024");



        reservation3 = new Reservation();
        reservation3.setResId(3L);
        reservation3.setBusConId(3L);
        reservation3.setPassengerName("Eder Militao");
        reservation3.setAddress("64, Rua Sao Paulo");
        reservation3.setCity("Sao Paulo");
        reservation3.setCountry("Brasil");
        reservation3.setZipCode("12345");
        reservation3.setCreditCardNumber("1234567890123456");
        reservation3.setCardHolderName("Eder Militao");
        reservation3.setCardExpirationMonth("07");
        reservation3.setCardExpirationYear("2025");
    }

    @Test
    @DisplayName("Test get all reservations")
    public void testGetAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);

        when(reservationRepository.findAll()).thenReturn(reservations);
        assertThat(reservationService.getAllReservations()).isEqualTo(reservations);
    }

    @Test
    @DisplayName("Test get reservation by id")
    public void testGetReservationById() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation1));
        assertThat(reservationService.getReservationById(1L)).isEqualTo(Optional.of(reservation1));
        
    }

    @Test
    @DisplayName("Test get reservations by bus connection id")
    public void testGetReservationsByBusConId() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);

        when(reservationRepository.findByBusConId(1L)).thenReturn(reservations);
        assertThat(reservationService.getReservationsByBusConId(1L)).isEqualTo(reservations);
        
    }

    @Test
    @DisplayName("Test save reservation")
    public void testSaveReservation() {
        when(reservationRepository.save(reservation1)).thenReturn(reservation1);
        assertThat(reservationService.saveReservation(reservation1)).isEqualTo(reservation1);
        
    }

    @Test
    @DisplayName("Test delete reservation")
    public void testDeleteReservation() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation1));
        reservationService.deleteReservation(1L);
        verify(reservationRepository, times(1)).delete(reservation1);
    }

    @Test
    @DisplayName("Test update reservation")
    public void testUpdateReservation() {
        Reservation updatedReservation = new Reservation();
        updatedReservation.setResId(1L);
        updatedReservation.setBusConId(1L);
        updatedReservation.setPassengerName("Vinicius Junior");
        updatedReservation.setAddress("64, Rua Madrid");
        updatedReservation.setCity("Madrid");
        updatedReservation.setCountry("Espanha");
        updatedReservation.setZipCode("12345");
        updatedReservation.setCreditCardNumber("1234567890123456");
        updatedReservation.setCardHolderName("Vinicius Junior");
        updatedReservation.setCardExpirationMonth("01");
        updatedReservation.setCardExpirationYear("2023");

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation1));
        when(reservationRepository.save(reservation1)).thenReturn(updatedReservation);
        assertThat(reservationService.updateReservation(1L, reservation1)).isEqualTo(updatedReservation);
    }

    @Test
    @DisplayName("Test save all reservations")
    public void testSaveAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        reservations.add(reservation3);

        when(reservationRepository.saveAll(reservations)).thenReturn(reservations);
        assertThat(reservationService.saveAllReservations(reservations)).isEqualTo(reservations);
    }

    @Test
    @DisplayName("Test delete reservation with invalid id")
    public void testDeleteReservationWithInvalidId() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        reservationService.deleteReservation(1L);
        verify(reservationRepository, times(0)).delete(reservation1);
    }

    @Test
    @DisplayName("Test update reservation with invalid id")
    public void testUpdateReservationWithInvalidId() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThat(reservationService.updateReservation(1L, reservation1)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test get reservation by id with invalid id")
    public void testGetReservationByIdWithInvalidId() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        assertThat(reservationService.getReservationById(1L)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test get reservations by bus connection id with invalid id")
    public void testGetReservationsByBusConIdWithInvalidId() {
        when(reservationRepository.findByBusConId(1L)).thenReturn(new ArrayList<>());
        assertThat(reservationService.getReservationsByBusConId(1L)).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Test get all reservations with no reservations")
    public void testGetAllReservationsWithNoReservations() {
        when(reservationRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(reservationService.getAllReservations()).isEqualTo(null);
    }
}
