package com.example.backend.repositoryTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import com.example.backend.model.BusConnection;
import com.example.backend.repository.BusConnectionRepository;
import com.example.backend.service.BusConnectionService;

@DataJpaTest
public class BusConnectionRepositoryTest {
    
    @Autowired
    private BusConnectionRepository busConnectionRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Test findByOriginAndDestinationAndDepartureDate")
    public void testFindByOriginAndDestinationAndDepartureDate() {
        BusConnection busConnection = new BusConnection("Aveiro", "Porto", "2021-06-01", "2021-06-01", "08:00:00", "10:20:00", 10.0, 10);
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOriginAndDestinationAndDepartureDate("Aveiro", "Porto", "2021-06-01");

        assertThat(found).contains(busConnection);
    }

    @Test
    @DisplayName("Test findByDestination")
    public void testFindByDestination() {
        BusConnection busConnection = new BusConnection("Aveiro", "Porto", "2021-06-01", "2021-06-01", "08:00:00", "10:20:00", 10.0, 10);
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByDestination("Porto");

        assertThat(found).contains(busConnection);
    }

    @Test
    @DisplayName("Test findByOriginAndDestination")
    public void testFindByOriginAndDestination() {
        BusConnection busConnection = new BusConnection("Aveiro", "Porto", "2021-06-01", "2021-06-01", "08:00:00", "10:20:00", 10.0, 10);
        entityManager.persist(busConnection);
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOriginAndDestination("origin", "destination");

        assertThat(found).contains(busConnection);
    }

    @Test
    @DisplayName("Test findByOrigin")
    public void testFindByOrigin() {
        BusConnection busConnection = new BusConnection("Aveiro", "Porto", "2021-06-01", "2021-06-01", "08:00:00", "10:20:00", 10.0, 10);
        entityManager.persist(busConnection);
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOrigin("origin");

        assertThat(found).contains(busConnection);
    }

    @Test
    @DisplayName("Test findByDepartureDate")
    public void testFindByDepartureDate() {
        BusConnection busConnection = new BusConnection("Aveiro", "Porto", "2021-06-01", "2021-06-01", "08:00:00", "10:20:00", 10.0, 10);
        entityManager.persist(busConnection);
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByDepartureDate("departureDate");

        assertThat(found).contains(busConnection);
    }
}
