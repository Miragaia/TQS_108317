package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.model.BusConnection;
import com.example.backend.repository.BusConnectionRepository;

import java.util.List;

@Service
public class BusConnectionService {
    
    @Autowired
    private BusConnectionRepository busConnectionRepository;
    
    public List<BusConnection> getAllBusConnections() {
        if (busConnectionRepository.findAll().isEmpty()) {
            return null;
        }
        return busConnectionRepository.findAll();
    }

    public BusConnection getBusConnectionById(Long conId) {
        return busConnectionRepository.findById(conId).orElse(null);
    }

    public List<BusConnection> getBusConnectionsByOriginAndDestinationAndDepartureDate(String origin, String destination, String departureDate) {
        if (busConnectionRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate).isEmpty()) {
            return null;
        }
        return busConnectionRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
    }

    public List<BusConnection> saveAllBusConnections(List<BusConnection> busConnections) {
        if (busConnections.isEmpty()) {
            return null;
        }
        return busConnectionRepository.saveAll(busConnections);
    }

    public BusConnection addBusConnection(BusConnection busConnection) {
        if (busConnection == null) {
            return null;
        }
        return busConnectionRepository.save(busConnection);
    }

    public BusConnection updateBusConnection(Long id, BusConnection busConnection) {
        BusConnection existingBusConnection = busConnectionRepository.findById(id).orElse(null);
        if (existingBusConnection != null) {
            existingBusConnection.setOrigin(busConnection.getOrigin());
            existingBusConnection.setDestination(busConnection.getDestination());
            existingBusConnection.setDepartureDate(busConnection.getDepartureDate());
            existingBusConnection.setDepartureTime(busConnection.getDepartureTime());
            existingBusConnection.setArrivalDate(busConnection.getArrivalDate());
            existingBusConnection.setArrivalTime(busConnection.getArrivalTime());
            existingBusConnection.setPrice(busConnection.getPrice());
            existingBusConnection.setTotalSeats(busConnection.getTotalSeats());
            return busConnectionRepository.save(existingBusConnection);
        }
        return null;
    }

    public void deleteBusConnection(Long id) {
        if (busConnectionRepository.findById(id).isEmpty()) {
            return;
        }
        busConnectionRepository.deleteById(id);
    }

    public List<BusConnection> getBusConnectionsByDestination(String destination) {
        if (busConnectionRepository.findByDestination(destination).isEmpty()) {
            return null;
        }
        return busConnectionRepository.findByDestination(destination);
    }

    public List<BusConnection> getBusConnectionsByOriginAndDestination(String origin, String destination) {
        if (busConnectionRepository.findByOriginAndDestination(origin, destination).isEmpty()) {
            return null;
        }
        return busConnectionRepository.findByOriginAndDestination(origin, destination);
    }

    public List<BusConnection> getBusConnectionsByOrigin(String origin) {
        if (busConnectionRepository.findByOrigin(origin).isEmpty()) {
            return null;
        }
        return busConnectionRepository.findByOrigin(origin);
    }

    public List<BusConnection> getBusConnectionsByDepartureDate(String departureDate) {
        if (busConnectionRepository.findByDepartureDate(departureDate).isEmpty()) {
            return null;
        }
        return busConnectionRepository.findByDepartureDate(departureDate);
    }
}
