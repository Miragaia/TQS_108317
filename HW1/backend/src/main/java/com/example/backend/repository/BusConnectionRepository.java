package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.BusConnection;

@Repository
public interface BusConnectionRepository extends JpaRepository<BusConnection, Long> {

    List<BusConnection> findByOrigin(String origin);

    List<BusConnection> findByDestination(String destination);

    List<BusConnection> findByOriginAndDestination(String origin, String destination);

    List<BusConnection> findByOriginAndDestinationAndDepartureDate(String origin, String destination, String departureDate);
}
