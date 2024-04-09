package com.example.backend.controllerTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.checkerframework.checker.units.qual.m;
import org.junit.jupiter.api.DisplayName;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.backend.controller.ReservationController;
import com.example.backend.model.Reservation;
import com.example.backend.service.ReservationService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.MediaType;

// Static imports
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    @DisplayName("Test get reservation by id")
    public void testGetReservationById() throws Exception {
        when(reservationService.getReservationById(1L)).thenReturn(Optional.of(new Reservation(1L, "Vinicius Junior", "64, Rua Madrid", "Madrid", "Espanha", "12345", "1234567890123456", "Vinicius Junior", "01", "2023")));
        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.busConId", is(1)))
                .andExpect(jsonPath("$.passengerName", is("Vinicius Junior")))
                .andExpect(jsonPath("$.address", is("64, Rua Madrid")))
                .andExpect(jsonPath("$.city", is("Madrid")))
                .andExpect(jsonPath("$.country", is("Espanha")))
                .andExpect(jsonPath("$.zipCode", is("12345")))
                .andExpect(jsonPath("$.creditCardNumber", is("1234567890123456")))
                .andExpect(jsonPath("$.cardHolderName", is("Vinicius Junior")))
                .andExpect(jsonPath("$.cardExpirationMonth", is("01")))
                .andExpect(jsonPath("$.cardExpirationYear", is("2023")));
        
        verify(reservationService, times(1)).getReservationById(1L);
    }

    @Test
    @DisplayName("Test create reservation")
    public void testCreateReservation() throws Exception {
        Reservation reservation = new Reservation(1L, "Vinicius Junior", "64, Rua Madrid", "Madrid", "Espanha", "12345", "1234567890123456", "Vinicius Junior", "01", "2023");
        when(reservationService.saveReservation(Mockito.any(Reservation.class))).thenReturn(reservation);
        mockMvc.perform(post("/api/reservations/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"busConId\": 1, \"passengerName\": \"Vinicius Junior\", \"address\": \"64, Rua Madrid\", \"city\": \"Madrid\", \"country\": \"Espanha\", \"zipCode\": \"12345\", \"creditCardNumber\": \"1234567890123456\", \"cardHolderName\": \"Vinicius Junior\", \"cardExpirationMonth\": \"01\", \"cardExpirationYear\": \"2023\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.busConId", is(1)))
                .andExpect(jsonPath("$.passengerName", is("Vinicius Junior")))
                .andExpect(jsonPath("$.address", is("64, Rua Madrid")))
                .andExpect(jsonPath("$.city", is("Madrid")))
                .andExpect(jsonPath("$.country", is("Espanha")))
                .andExpect(jsonPath("$.zipCode", is("12345")))
                .andExpect(jsonPath("$.creditCardNumber", is("1234567890123456")))
                .andExpect(jsonPath("$.cardHolderName", is("Vinicius Junior")))
                .andExpect(jsonPath("$.cardExpirationMonth", is("01")))
                .andExpect(jsonPath("$.cardExpirationYear", is("2023")));

        verify(reservationService, times(1)).saveReservation(Mockito.any(Reservation.class));
    }

    @Test
    @DisplayName("Test get reservations by bus connection id")
    public void testGetReservationsByBusConId() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, "Vinicius Junior", "64, Rua Madrid", "Madrid", "Espanha", "12345", "1234567890123456", "Vinicius Junior", "01", "2023"));
        reservations.add(new Reservation(2L, "Raphael Varane", "64, Rua Manchester", "Manchester", "Inglaterra", "1234567", "123456789012345667", "Raphael Varane", "01", "2023"));
        when(reservationService.getReservationsByBusConId(1L)).thenReturn(reservations);
        mockMvc.perform(get("/api/reservations/bus-connections/1/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].busConId", is(1)))
                .andExpect(jsonPath("$[0].passengerName", is("Vinicius Junior")))
                .andExpect(jsonPath("$[0].address", is("64, Rua Madrid")))
                .andExpect(jsonPath("$[0].city", is("Madrid")))
                .andExpect(jsonPath("$[0].country", is("Espanha")))
                .andExpect(jsonPath("$[0].zipCode", is("12345")))
                .andExpect(jsonPath("$[0].creditCardNumber", is("1234567890123456")))
                .andExpect(jsonPath("$[0].cardHolderName", is("Vinicius Junior")))
                .andExpect(jsonPath("$[0].cardExpirationMonth", is("01")))
                .andExpect(jsonPath("$[0].cardExpirationYear", is("2023")))
                .andExpect(jsonPath("$[1].busConId", is(2)))
                .andExpect(jsonPath("$[1].passengerName", is("Raphael Varane")))
                .andExpect(jsonPath("$[1].address", is("64, Rua Manchester")))
                .andExpect(jsonPath("$[1].city", is("Manchester")))
                .andExpect(jsonPath("$[1].country", is("Inglaterra")))
                .andExpect(jsonPath("$[1].zipCode", is("1234567")))
                .andExpect(jsonPath("$[1].creditCardNumber", is("123456789012345667")))
                .andExpect(jsonPath("$[1].cardHolderName", is("Raphael Varane")))
                .andExpect(jsonPath("$[1].cardExpirationMonth", is("01")))
                .andExpect(jsonPath("$[1].cardExpirationYear", is("2023")));

        verify(reservationService, times(1)).getReservationsByBusConId(1L);
    
    }

    @Test
    @DisplayName("Test get all reservations")
    public void testGetAllReservations() throws Exception {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation(1L, "Vinicius Junior", "64, Rua Madrid", "Madrid", "Espanha", "12345", "1234567890123456", "Vinicius Junior", "01", "2023"));
        reservations.add(new Reservation(2L, "Raphael Varane", "64, Rua Manchester", "Manchester", "Inglaterra", "1234567", "123456789012345667", "Raphael Varane", "01", "2023"));
        when(reservationService.getAllReservations()).thenReturn(reservations);
        mockMvc.perform(get("/api/reservations/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].busConId", is(1)))
                .andExpect(jsonPath("$[0].passengerName", is("Vinicius Junior")))
                .andExpect(jsonPath("$[0].address", is("64, Rua Madrid")))
                .andExpect(jsonPath("$[0].city", is("Madrid")))
                .andExpect(jsonPath("$[0].country", is("Espanha")))
                .andExpect(jsonPath("$[0].zipCode", is("12345")))
                .andExpect(jsonPath("$[0].creditCardNumber", is("1234567890123456")))
                .andExpect(jsonPath("$[0].cardHolderName", is("Vinicius Junior")))
                .andExpect(jsonPath("$[0].cardExpirationMonth", is("01")))
                .andExpect(jsonPath("$[0].cardExpirationYear", is("2023")))
                .andExpect(jsonPath("$[1].busConId", is(2)))
                .andExpect(jsonPath("$[1].passengerName", is("Raphael Varane")))
                .andExpect(jsonPath("$[1].address", is("64, Rua Manchester")))
                .andExpect(jsonPath("$[1].city", is("Manchester")))
                .andExpect(jsonPath("$[1].country", is("Inglaterra")))
                .andExpect(jsonPath("$[1].zipCode", is("1234567")))
                .andExpect(jsonPath("$[1].creditCardNumber", is("123456789012345667")))
                .andExpect(jsonPath("$[1].cardHolderName", is("Raphael Varane")))
                .andExpect(jsonPath("$[1].cardExpirationMonth", is("01")))
                .andExpect(jsonPath("$[1].cardExpirationYear", is("2023")));

        verify(reservationService, times(1)).getAllReservations();
    }

    @Test
    @DisplayName("Test delete reservation by id")
    public void testDeleteReservation() throws Exception {
        Reservation reservation = new Reservation(1L, "Vinicius Junior", "64, Rua Madrid", "Madrid", "Espanha", "12345", "1234567890123456", "Vinicius Junior", "01", "2023");
        when(reservationService.getReservationById(1L)).thenReturn(Optional.of(reservation));
        doNothing().when(reservationService).deleteReservation(1L);
        mockMvc.perform(delete("/api/reservations/delete/1"))
                .andExpect(status().isOk());

        verify(reservationService, times(1)).deleteReservation(1L);
    }

    @Test
    @DisplayName("Test update reservation")
    public void testUpdateReservation() throws Exception {
        Reservation initialReservation = new Reservation(1L, "Vinicius Junior", "64, Rua Madrid", "Madrid", "Espanha", "12345", "1234567890123456", "Vinicius Junior", "01", "2023");
        Reservation updatedReservation = new Reservation(1L, "Raphael Varane", "64, Rua Manchester", "Manchester", "Inglaterra", "1234567", "123456789012345667", "Raphael Varane", "02", "2024");

        when(reservationService.getReservationById(1L)).thenReturn(Optional.of(initialReservation));
        when(reservationService.updateReservation(eq(1L), any(Reservation.class))).thenReturn(updatedReservation);

        mockMvc.perform(put("/api/reservations/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"busConId\": 1, \"passengerName\": \"Raphael Varane\", \"address\": \"64, Rua Manchester\", \"city\": \"Manchester\", \"country\": \"Inglaterra\", \"zipCode\": \"1234567\", \"creditCardNumber\": \"123456789012345667\", \"cardHolderName\": \"Raphael Varane\", \"cardExpirationMonth\": \"02\", \"cardExpirationYear\": \"2024\" }"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.busConId", is(1)))
            .andExpect(jsonPath("$.passengerName", is("Raphael Varane")))
            .andExpect(jsonPath("$.address", is("64, Rua Manchester")))
            .andExpect(jsonPath("$.city", is("Manchester")))
            .andExpect(jsonPath("$.country", is("Inglaterra")))
            .andExpect(jsonPath("$.zipCode", is("1234567")))
            .andExpect(jsonPath("$.creditCardNumber", is("123456789012345667")))
            .andExpect(jsonPath("$.cardHolderName", is("Raphael Varane")))
            .andExpect(jsonPath("$.cardExpirationMonth", is("02")))
            .andExpect(jsonPath("$.cardExpirationYear", is("2024")));

        verify(reservationService, times(1)).updateReservation(eq(1L), any(Reservation.class));
        verify(reservationService, times(1)).getReservationById(1L);
    }

    @Test
    @DisplayName("Test update reservation with invalid id")
    public void testUpdateReservationWithInvalidId() throws Exception {
        when(reservationService.getReservationById(1L)).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/reservations/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"busConId\": 1, \"passengerName\": \"Raphael Varane\", \"address\": \"64, Rua Manchester\", \"city\": \"Manchester\", \"country\": \"Inglaterra\", \"zipCode\": \"1234567\", \"creditCardNumber\": \"123456789012345667\", \"cardHolderName\": \"Raphael Varane\", \"cardExpirationMonth\": \"02\", \"cardExpirationYear\": \"2024\" }"))
            .andExpect(status().isNotFound());

        verify(reservationService, times(1)).getReservationById(1L);
        verify(reservationService, times(0)).updateReservation(eq(1L), any(Reservation.class));
    }

    @Test
    @DisplayName("Test delete reservation with invalid id")
    public void testDeleteReservationWithInvalidId() throws Exception {
        // Given
        when(reservationService.getReservationById(999L)).thenReturn(Optional.empty());

        // When and Then
        mockMvc.perform(delete("/api/reservations/delete/999"))
                .andExpect(status().isNotFound());

        verify(reservationService, never()).deleteReservation(999L);
    }

    @Test
    @DisplayName("Test get reservation by id with invalid id")
    public void testGetReservationByIdWithInvalidId() throws Exception {
        // Given
        when(reservationService.getReservationById(999L)).thenReturn(Optional.empty());

        // When and Then
        mockMvc.perform(get("/api/reservations/999"))
                .andExpect(status().isNotFound());

        verify(reservationService, times(1)).getReservationById(999L);
    }

    @Test
    @DisplayName("Test get reservations by bus connection id with invalid id")
    public void testGetReservationsByBusConIdWithInvalidId() throws Exception {
        // Given
        when(reservationService.getReservationsByBusConId(999L)).thenReturn(Collections.emptyList());

        // When and Then
        mockMvc.perform(get("/api/reservations/bus-connections/999/reservations"))
                .andExpect(status().isNoContent());

        verify(reservationService, times(1)).getReservationsByBusConId(999L);
    }
}
