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
        return busConnectionRepository.findAll();
    }

    // public BusConnection getBusConnectionById(Long conId) {
    //     return busConnectionRepository.findById(conId).orElse(null);
    // }

    public List<BusConnection> getBusConnectionsByOriginAndDestinationAndDepartureDate(String origin, String destination, String departureDate) {
        return busConnectionRepository.findByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
    }

    public List<BusConnection> saveAllBusConnections(List<BusConnection> busConnections) {
        return busConnectionRepository.saveAll(busConnections);
    }
}
