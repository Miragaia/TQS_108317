package com.example.backend.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.backend.model.BusConnection;
import com.example.backend.repository.BusConnectionRepository;
import com.example.backend.service.BusConnectionService;

@ExtendWith(MockitoExtension.class)
public class BusConnectionServiceTest {
    
    @Mock
    private BusConnectionRepository busConnectionRepository;

    @InjectMocks
    private BusConnectionService busConnectionService;

    private BusConnection busConnection1;
    private BusConnection busConnection2;
    private BusConnection busConnection3;

    @BeforeEach
    void setUp() {
        busConnection1 = new BusConnection();
        busConnection1.setConId(1L);
        busConnection1.setOrigin("Viseu");
        busConnection1.setDestination("Aveiro");
        busConnection1.setDepartureDate("2024-04-27");
        busConnection1.setDepartureTime("13:00:00");
        busConnection1.setArrivalDate("2024-04-27");
        busConnection1.setArrivalTime("14:30:00");
        busConnection1.setPrice(20);
        busConnection1.setTotalSeats(20);

        busConnection2 = new BusConnection();
        busConnection2.setConId(2L);
        busConnection2.setOrigin("Aveiro");
        busConnection2.setDestination("Viseu");
        busConnection2.setDepartureDate("2024-04-28");
        busConnection2.setDepartureTime("15:00:00");
        busConnection2.setArrivalDate("2024-04-28");
        busConnection2.setArrivalTime("15:30:00");
        busConnection2.setPrice(30);
        busConnection2.setTotalSeats(2);

        busConnection3 = new BusConnection();
        busConnection3.setConId(3L);
        busConnection3.setOrigin("Viseu");
        busConnection3.setDestination("Coimbra");
        busConnection3.setDepartureDate("2024-04-30");
        busConnection3.setDepartureTime("11:00:00");
        busConnection3.setArrivalDate("2024-04-30");
        busConnection3.setArrivalTime("11:30:00");
        busConnection3.setPrice(35);
        busConnection3.setTotalSeats(3);
    }

    
    @Test
    @DisplayName("Test get all bus connections")
    public void testGetAllBusConnections() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(busConnection1);
        busConnections.add(busConnection2);
        busConnections.add(busConnection3);

        when(busConnectionRepository.findAll()).thenReturn(busConnections);
        assertThat(busConnectionService.getAllBusConnections()).isEqualTo(busConnections);
    }
        
    
    @Test
    @DisplayName("Test get bus connection by id")
    public void testGetBusConnectionById() {
        when(busConnectionRepository.findById(1L)).thenReturn(Optional.of(busConnection1));
        assertThat(busConnectionService.getBusConnectionById(1L)).isEqualTo(busConnection1);
    }
    
    @Test
    @DisplayName("Test get bus connections by origin, destination and departure date")
    public void testGetBusConnectionsByOriginAndDestinationAndDepartureDate() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(busConnection1);
        busConnections.add(busConnection2);

        when(busConnectionRepository.findByOriginAndDestinationAndDepartureDate("Viseu", "Aveiro", "2024-04-27")).thenReturn(busConnections);
        assertThat(busConnectionService.getBusConnectionsByOriginAndDestinationAndDepartureDate("Viseu", "Aveiro", "2024-04-27")).isEqualTo(busConnections);
    }
    
    @Test
    @DisplayName("Test save bus connection")
    public void testSaveAllBusConnections() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(busConnection1);
        busConnections.add(busConnection2);

        when(busConnectionRepository.saveAll(busConnections)).thenReturn(busConnections);
        assertThat(busConnectionService.saveAllBusConnections(busConnections)).isEqualTo(busConnections);
    }


    @Test
    @DisplayName("Test add bus connection")
    public void testAddBusConnection() {
        when(busConnectionRepository.save(busConnection1)).thenReturn(busConnection1);
        assertThat(busConnectionService.addBusConnection(busConnection1)).isEqualTo(busConnection1);
    }

    @Test
    @DisplayName("Test update bus connection")
    public void testUpdateBusConnection() {
        BusConnection updatedBusConnection = new BusConnection();
        updatedBusConnection.setConId(1L);
        updatedBusConnection.setOrigin("Viseu");
        updatedBusConnection.setDestination("Aveiro");
        updatedBusConnection.setDepartureDate("2024-04-27");
        updatedBusConnection.setDepartureTime("13:00:00");
        updatedBusConnection.setArrivalDate("2024-04-27");
        updatedBusConnection.setArrivalTime("14:30:00");
        updatedBusConnection.setPrice(25);
        updatedBusConnection.setTotalSeats(20);

        when(busConnectionRepository.findById(1L)).thenReturn(Optional.of(busConnection1));
        when(busConnectionRepository.save(busConnection1)).thenReturn(updatedBusConnection);
        assertThat(busConnectionService.updateBusConnection(1L, busConnection1)).isEqualTo(updatedBusConnection);
    }

    @Test
    @DisplayName("Test delete bus connection")
    public void testDeleteBusConnection() {
        when(busConnectionRepository.findById(1L)).thenReturn(Optional.of(busConnection1));
        doNothing().when(busConnectionRepository).deleteById(1L);
        
        busConnectionService.deleteBusConnection(1L);
        
        verify(busConnectionRepository, times(1)).findById(1L);
        verify(busConnectionRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Test delete bus connection with invalid id")
    public void testDeleteBusConnectionInvalidId() {
        when(busConnectionRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        
        busConnectionService.deleteBusConnection(1L);
        
        verify(busConnectionRepository, times(1)).findById(1L);
        verify(busConnectionRepository, never()).deleteById(1L);
    }

    @Test
    @DisplayName("Test get bus connection by departure date")
    public void testGetBusConnectionByDepartureDate() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(busConnection1);
        busConnections.add(busConnection3);

        when(busConnectionRepository.findByDepartureDate("2024-04-27")).thenReturn(busConnections);
        assertThat(busConnectionService.getBusConnectionsByDepartureDate("2024-04-27")).isEqualTo(busConnections);
    }

    @Test
    @DisplayName("Test get bus connection by origin")
    public void testGetBusConnectionByOrigin() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(busConnection1);
        busConnections.add(busConnection3);

        when(busConnectionRepository.findByOrigin("Viseu")).thenReturn(busConnections);
        assertThat(busConnectionService.getBusConnectionsByOrigin("Viseu")).isEqualTo(busConnections);
    }

    @Test
    @DisplayName("Test get bus connection by destination")
    public void testGetBusConnectionByDestination() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(busConnection1);
        busConnections.add(busConnection2);

        when(busConnectionRepository.findByDestination("Aveiro")).thenReturn(busConnections);
        assertThat(busConnectionService.getBusConnectionsByDestination("Aveiro")).isEqualTo(busConnections);
    }

    @Test
    @DisplayName("Test get bus connection by origin and destination")
    public void testGetBusConnectionByOriginAndDestination() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(busConnection1);

        when(busConnectionRepository.findByOriginAndDestination("Viseu", "Aveiro")).thenReturn(busConnections);
        assertThat(busConnectionService.getBusConnectionsByOriginAndDestination("Viseu", "Aveiro")).isEqualTo(busConnections);
    }

    @Test
    @DisplayName("Test get bus connection by origin and destination with invalid parameters")
    public void testGetBusConnectionByOriginAndDestinationWithInvalidParameters() {
        // Assume bus connections with origin "Viseu" and destination "Aveiro" do not exist in the repository
        when(busConnectionRepository.findByOriginAndDestination("Viseu", "Aveiro")).thenReturn(new ArrayList<BusConnection>());

        assertThat(busConnectionService.getBusConnectionsByOriginAndDestination("Viseu", "Aveiro")).isEqualTo(null);
    }

    @Test
    @DisplayName("Test delete bus connection with invalid id")
    public void testDeleteBusConnectionWithInvalidId() {
        // Assume bus connection with ID 1 does not exist in the repository
        when(busConnectionRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        busConnectionService.deleteBusConnection(1L);

        // Verify that findById method is invoked
        verify(busConnectionRepository, times(1)).findById(1L);
        // Verify that deleteById method is not invoked
        verify(busConnectionRepository, never()).deleteById(1L);
    }

    @Test
    @DisplayName("Test get bus connection by id with invalid id")
    public void testGetBusConnectionByIdWithInvalidId() {
        // Assume bus connection with ID 1 does not exist in the repository
        when(busConnectionRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        assertThat(busConnectionService.getBusConnectionById(1L)).isEqualTo(null);
    }

    @Test
    @DisplayName("Test get bus connections by origin, destination and departure date with invalid parameters")
    public void testGetBusConnectionsByOriginAndDestinationAndDepartureDateWithInvalidParameters() {
        // Assume bus connections with origin "Viseu", destination "Aveiro" and departure date "2024-04-27" do not exist in the repository
        when(busConnectionRepository.findByOriginAndDestinationAndDepartureDate("Viseu", "Aveiro", "2024-04-27")).thenReturn(new ArrayList<BusConnection>());

        assertThat(busConnectionService.getBusConnectionsByOriginAndDestinationAndDepartureDate("Viseu", "Aveiro", "2024-04-27")).isEqualTo(null);
    }

}
