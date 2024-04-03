package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.BusConnection;
import com.example.backend.service.BusConnectionService;

import java.util.List;

@RestController
@RequestMapping("/api/bus-connections")
public class BusConnectionController {
    
    @Autowired
    private BusConnectionService busConnectionService;
    
    @GetMapping
    public ResponseEntity<List<BusConnection>> getAllBusConnections() {
        List<BusConnection> busConnections = busConnectionService.getAllBusConnections();
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }
    
    // Implement other endpoints as needed
}
