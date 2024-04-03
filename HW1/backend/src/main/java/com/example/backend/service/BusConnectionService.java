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
    
    public BusConnection saveBusConnection(BusConnection busConnection) {           //confirmar busConnection
        return busConnectionRepository.save(busConnection);     
    }

    public void deleteBusConnection(Long conId) {
        busConnectionRepository.deleteById(conId);
    }

    public BusConnection getBusConnectionById(Long conId) {
        return busConnectionRepository.findById(conId).orElse(null);
    }

    public List<BusConnection> getBusConnectionsByOrigin(String origin) {
        return busConnectionRepository.findByOrigin(origin);
    }

    public List<BusConnection> getBusConnectionsByDestination(String destination) {
        return busConnectionRepository.findByDestination(destination);
    }

    public List<BusConnection> getBusConnectionsByOriginAndDestination(String origin, String destination) {
        return busConnectionRepository.findByOriginAndDestination(origin, destination);
    }
}
