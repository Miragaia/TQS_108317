package com.example.backend.UnitTests;

import static org.mockito.Mockito.*;

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
        busConnections.add(new BusConnection());
        busConnections.add(new BusConnection());
        busConnections.add(new BusConnection());
        
        when(busConnectionRepository.findAll()).thenReturn(busConnections);
        
        List<BusConnection> result = busConnectionService.getAllBusConnections();
        
        assert(result.size() == 3);
    }
    
    @Test
    @DisplayName("Test get bus connection by id")
    public void testGetBusConnectionById() {
        BusConnection busConnection = new BusConnection();
        busConnection.setConId(1L);
        
        when(busConnectionRepository.findById(1L)).thenReturn(Optional.of(busConnection));
        
        BusConnection result = busConnectionService.getBusConnectionById(1L);
        
        assert(result.getConId() == 1L);
    }
    
    @Test
    @DisplayName("Test get bus connections by origin, destination and departure date")
    public void testGetBusConnectionsByOriginAndDestinationAndDepartureDate() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(new BusConnection());
        busConnections.add(new BusConnection());
        busConnections.add(new BusConnection());
        
        when(busConnectionRepository.findByOriginAndDestinationAndDepartureDate("origin", "destination", "departureDate")).thenReturn(busConnections);
        
        List<BusConnection> result = busConnectionService.getBusConnectionsByOriginAndDestinationAndDepartureDate("origin", "destination", "departureDate");
        
        assert(result.size() == 3);
    }
    
    @Test
    @DisplayName("Test save bus connection")
    public void testSaveAllBusConnections() {
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(new BusConnection());
        busConnections.add(new BusConnection());
        busConnections.add(new BusConnection());
        
        when(busConnectionRepository.saveAll(busConnections)).thenReturn(busConnections);
        
        List<BusConnection> result = busConnectionService.saveAllBusConnections(busConnections);
        
        assert(result.size() == 3);
    }


    @Test
    @DisplayName("Test add bus connection")
    public void testAddBusConnection() {
        BusConnection busConnection = new BusConnection();
        busConnection.setConId(1L);
        
        when(busConnectionRepository.save(busConnection)).thenReturn(busConnection);
        
        BusConnection result = busConnectionService.addBusConnection(busConnection);
        
        assert(result.getConId() == 1L);
    }
}
