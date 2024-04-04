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

    // @GetMapping("/origin/{origin}/destination/{destination}")
    // public ResponseEntity<List<BusConnection>> getBusConnectionByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
    //     List<BusConnection> busConnections = busConnectionService.getBusConnectionsByOriginAndDestination(origin, destination);
    //     return new ResponseEntity<>(busConnections, HttpStatus.OK);
    // }

    @GetMapping("/origin/{origin}/destination/{destination}/departure-date/{departureDate}")
    public ResponseEntity<List<BusConnection>> getBusConnectionByOriginAndDestinationAndDepartureDate(@PathVariable String origin, @PathVariable String destination, @PathVariable String departureDate) {
        List<BusConnection> busConnections = busConnectionService.getBusConnectionsByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }
}
