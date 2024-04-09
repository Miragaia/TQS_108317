package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.BusConnection;
import com.example.backend.service.BusConnectionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/bus-connections")
public class BusConnectionController {
    
    private static final Logger logger = LogManager.getLogger(BusConnectionController.class);

    @Autowired
    private BusConnectionService busConnectionService;
    
    @GetMapping
    public ResponseEntity<List<BusConnection>> getAllBusConnections() {
        logger.info("Fetching all bus connections.");
        List<BusConnection> busConnections = busConnectionService.getAllBusConnections();
        if (busConnections.isEmpty()) {
            logger.info("No bus connections found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(busConnections.size() + " bus connections found.");
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/origin/{origin}")
    public ResponseEntity<?> getBusConnectionsByOrigin(@PathVariable String origin) {
        if (origin == null) {
            logger.info("No origin provided.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetching bus connections by origin {}.", origin);
        List<BusConnection> busConnections = busConnectionService.getBusConnectionsByOrigin(origin);
        if (busConnections.isEmpty()) {
            logger.info("No bus connections found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(busConnections.size() + " bus connections found.");
        logger.info("Bus connections: " + busConnections);
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/destination/{destination}")
    public ResponseEntity<?> getBusConnectionsByDestination(@PathVariable String destination) {
        if (destination == null) {
            logger.info("No destination provided.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetching bus connections by destination {}.", destination);
        List<BusConnection> busConnections = busConnectionService.getBusConnectionsByDestination(destination);
        if (busConnections.isEmpty()) {
            logger.info("No bus connections found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(busConnections.size() + " bus connections found.");
        logger.info("Bus connections: " + busConnections);
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/departure-date/{departureDate}")
    public ResponseEntity<?> getBusConnectionsByDepartureDate(@PathVariable String departureDate) {
        if (departureDate == null) {
            logger.info("No departure date provided.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetching bus connections by departure date {}.", departureDate);
        List<BusConnection> busConnections = busConnectionService.getBusConnectionsByDepartureDate(departureDate);
        if (busConnections.isEmpty()) {
            logger.info("No bus connections found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(busConnections.size() + " bus connections found.");
        logger.info("Bus connections: " + busConnections);
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/origin/{origin}/destination/{destination}")
    public ResponseEntity<?> getBusConnectionsByOriginAndDestination(@PathVariable String origin, @PathVariable String destination) {
        if (origin == null || destination == null) {
            logger.info("Missing parameters.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetching bus connections by origin {} and destination {}.", origin, destination);
        List<BusConnection> busConnections = busConnectionService.getBusConnectionsByOriginAndDestination(origin, destination);
        if (busConnections.isEmpty()) {
            logger.info("No bus connections found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(busConnections.size() + " bus connections found.");
        logger.info("Bus connections: " + busConnections);
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/{origin}/{destination}/{departureDate}")
    public ResponseEntity<?> getBusConnectionByOriginAndDestinationAndDepartureDate(@PathVariable String origin, @PathVariable String destination, @PathVariable String departureDate) {
        if (origin == null || destination == null || departureDate == null) {
            logger.info("Missing parameters.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetching bus connections by origin {}, destination {}, and departure date {}.", origin, destination, departureDate);
        List<BusConnection> busConnections = busConnectionService.getBusConnectionsByOriginAndDestinationAndDepartureDate(origin, destination, departureDate);
        if (busConnections.isEmpty()) {
            logger.info("No bus connections found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info(busConnections.size() + " bus connections found.");
        logger.info("Bus connections: " + busConnections);
        return new ResponseEntity<>(busConnections, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusConnection> getBusConnectionById(@PathVariable Long id) {
        if (id == null) {
            logger.info("No id provided.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Fetching bus connection by id {}.", id);
        BusConnection busConnection = busConnectionService.getBusConnectionById(id);
        if (busConnection == null) {
            logger.info("Bus connection with id {} not found.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(busConnection, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BusConnection> addBusConnection(@RequestBody BusConnection busConnection) {
        if (busConnection == null) {
            logger.info("No bus connection to save.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Adding bus connection: " + busConnection);
        BusConnection newBusConnection = busConnectionService.addBusConnection(busConnection);
        if (newBusConnection == null) {
            logger.error("Error adding bus connection.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newBusConnection, HttpStatus.CREATED);
    }

    @PostMapping("/add-all")
    public ResponseEntity<List<BusConnection>> addAllBusConnections(@RequestBody List<BusConnection> busConnections) {
        if (busConnections.isEmpty()) {
            logger.info("No bus connections to save.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Adding all bus connections: " + busConnections);
        List<BusConnection> newBusConnections = busConnectionService.saveAllBusConnections(busConnections);
        if (newBusConnections == null) {
            logger.error("Error adding bus connections.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(newBusConnections, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BusConnection> updateBusConnection(@PathVariable Long id, @RequestBody BusConnection busConnection) {
        logger.info("Updating bus connection with id {}.", id);
        if (busConnectionService.getBusConnectionById(id) == null) {
            logger.info("Bus connection with id {} not found.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BusConnection updatedBusConnection = busConnectionService.updateBusConnection(id, busConnection);
        logger.info("Bus connection updated: " + updatedBusConnection);
        return new ResponseEntity<>(updatedBusConnection, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBusConnection(@PathVariable Long id) {
        if (id == null) {
            logger.info("No id provided.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Deleting bus connection with id {}.", id);
        if (busConnectionService.getBusConnectionById(id) == null) {
            logger.info("Bus connection with id {} not found.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        busConnectionService.deleteBusConnection(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
