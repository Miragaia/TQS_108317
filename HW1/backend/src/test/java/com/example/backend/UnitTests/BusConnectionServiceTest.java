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

import com.example.backend.model.BusConnection;
import com.example.backend.repository.BusConnectionRepository;
import com.example.backend.service.BusConnectionService;

@ExtendWith(MockitoExtension.class)
public class BusConnectionServiceTest {
    
    @Mock
    private BusConnectionRepository busConnectionRepository;

    @InjectMocks
    private BusConnectionService busConnectionService;

    
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


    //fazer testes para os erros?
}
