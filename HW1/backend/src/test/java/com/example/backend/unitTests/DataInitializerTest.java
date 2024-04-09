package com.example.backend.unitTests;

import com.example.backend.data.DataInitializer;
import com.example.backend.model.BusConnection;
import com.example.backend.service.BusConnectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class DataInitializerTest {

    @Mock
    private BusConnectionService busConnectionService;

    @InjectMocks
    private DataInitializer dataInitializer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testDataInitializationWhenNotEmpty() throws Exception {
        // Arrange
        when(busConnectionService.getAllBusConnections()).thenReturn(Arrays.asList(new BusConnection("Viseu", "Aveiro", "2024-04-12", "2024-04-12", "09:00:00", "18:00:00", 120.00, 1)));

        // Act
        dataInitializer.run();

        // Assert
        verify(busConnectionService, never()).saveAllBusConnections(any(List.class));
    }
}
