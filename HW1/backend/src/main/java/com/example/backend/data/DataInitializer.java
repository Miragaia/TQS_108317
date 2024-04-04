package com.example.backend.data;

import com.example.backend.model.BusConnection;
import com.example.backend.service.BusConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BusConnectionService busConnectionService;

    @Override
    public void run(String... args) throws Exception {
        // Sample bus connections
        List<BusConnection> connections = Arrays.asList(
            new BusConnection("New York", "Los Angeles", "2024-04-10 08:00:00", "2024-04-10 16:00:00", 100.00),
            new BusConnection("Chicago", "Houston", "2024-04-12 09:00:00", "2024-04-12 18:00:00", 120.00),
            new BusConnection("San Francisco", "Seattle", "2024-04-15 10:00:00", "2024-04-15 20:00:00", 80.00)
        );

        // Save the bus connections
        busConnectionService.saveAllBusConnections(connections);
    }
}
