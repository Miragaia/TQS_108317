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
            new BusConnection("Viseu", "Aveiro", "2024-04-10 08:00:00", "2024-04-10 16:00:00", 100.00),
            new BusConnection("Viseu", "Aveiro", "2024-04-12 09:00:00", "2024-04-12 18:00:00", 120.00),
            new BusConnection("Viseu", "Coimbra", "2024-04-15 10:00:00", "2024-04-15 20:00:00", 80.00),
            new BusConnection("Viseu", "Coimbra", "2024-04-17 11:00:00", "2024-04-17 22:00:00", 90.00),
            new BusConnection("Viseu", "Porto", "2024-05-15 22:00:00", "2024-05-15 09:00:00", 125.00),
            new BusConnection("Aveiro", "Lisboa", "2024-04-20 12:00:00", "2024-04-20 23:00:00", 70.00),
            new BusConnection("Aveiro", "Viseu", "2024-04-22 13:00:00", "2024-04-22 00:00:00", 75.00),
            new BusConnection("Aveiro", "Porto", "2024-05-17 23:00:00", "2024-05-17 10:00:00", 135.00),
            new BusConnection("Coimbra", "Viseu", "2024-04-25 14:00:00", "2024-04-25 01:00:00", 85.00),
            new BusConnection("Coimbra", "Viseu", "2024-04-27 15:00:00", "2024-04-27 02:00:00", 95.00),
            new BusConnection("Coimbra", "Porto", "2024-05-20 00:00:00", "2024-05-20 11:00:00", 145.00),
            new BusConnection("Lisboa", "Aveiro", "2024-04-30 16:00:00", "2024-04-30 03:00:00", 65.00),
            new BusConnection("Lisboa", "Coimbra", "2024-05-02 17:00:00", "2024-05-02 04:00:00", 75.00),
            new BusConnection("Lisboa", "Porto", "2024-05-22 01:00:00", "2024-05-22 12:00:00", 155.00),
            new BusConnection("Porto", "Coimbra", "2024-05-05 18:00:00", "2024-05-05 05:00:00", 85.00),
            new BusConnection("Porto", "Lisboa", "2024-05-07 19:00:00", "2024-05-07 06:00:00", 95.00),
            new BusConnection("Porto", "Viseu", "2024-05-10 20:00:00", "2024-05-10 07:00:00", 105.00),
            new BusConnection("Porto", "Aveiro", "2024-05-12 21:00:00", "2024-05-12 08:00:00", 115.00)
        );

        // Save the bus connections
        busConnectionService.saveAllBusConnections(connections);
    }
}
