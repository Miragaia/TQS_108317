package com.example.backend.repositoryTests;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BusConnectionRepositoryTest {
    
    @Autowired
    private BusConnectionRepository busConnectionRepository;

    @Autowired
    private TestEntityManager entityManager;

    private BusConnection busConnection;
    private BusConnection busConnection2;

    @BeforeEach
    public void setUp() {
        busConnection = new BusConnection("Aveiro", "Porto", "2021-06-01", "2021-06-01", "08:00:00", "10:20:00", 10.0, 10);
        busConnection2 = new BusConnection("Algarve", "Porto", "2021-07-01", "2021-06-01", "08:00:00", "10:20:00", 10.0, 10);
    }

    @AfterEach
    public void tearDown() {
        busConnectionRepository.delete(busConnection);
        busConnectionRepository.delete(busConnection2);
    }

    @Test
    @DisplayName("Test findByOriginAndDestinationAndDepartureDate")
    public void testFindByOriginAndDestinationAndDepartureDate() {
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOriginAndDestinationAndDepartureDate("Aveiro", "Porto", "2021-06-01");

        assertThat(found).contains(busConnection);
    }

    @Test
    @DisplayName("Test findByDestination")
    public void testFindByDestination() {
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

        List<BusConnection> found = busConnectionRepository.findByOriginAndDestination("Aveiro", "Porto");

        assertThat(found).contains(busConnection);
    }

    @Test
    @DisplayName("Test findByOrigin")
    public void testFindByOrigin() {
        entityManager.persist(busConnection);
        entityManager.persist(busConnection2);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOrigin("Algarve");

        assertThat(found).contains(busConnection2);
    }

    @Test
    @DisplayName("Test findByDepartureDate")
    public void testFindByDepartureDate() {
        entityManager.persist(busConnection);
        entityManager.persist(busConnection2);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByDepartureDate("2021-06-01");

        assertThat(found).contains(busConnection);
    }

    @Test
    @DisplayName("Test findByOriginAndDestinationAndDepartureDate with no results")
    public void testFindByOriginAndDestinationAndDepartureDateNoResults() {
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOriginAndDestinationAndDepartureDate("Algarve", "Porto", "2021-06-02");

        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Test findByDestination with no results")
    public void testFindByDestinationNoResults() {
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByDestination("Algarve");

        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Test findByOriginAndDestination with no results")
    public void testFindByOriginAndDestinationNoResults() {
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOriginAndDestination("Algarve", "Lisboa");

        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Test findByOrigin with no results")
    public void testFindByOriginNoResults() {
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByOrigin("Algarve");

        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Test findByDepartureDate with no results")
    public void testFindByDepartureDateNoResults() {
        entityManager.persist(busConnection);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findByDepartureDate("2021-11-02");

        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Test find all bus connections")
    public void testFindAll() {
        Integer totalBusCons = busConnectionRepository.findAll().size();
        entityManager.persist(busConnection);
        entityManager.persist(busConnection2);
        entityManager.flush();

        List<BusConnection> found = busConnectionRepository.findAll();

        assertThat(found).contains(busConnection, busConnection2);
        assertThat(found.size()).isEqualTo(totalBusCons + 2);
    }

    @Test
    @DisplayName("Test find bus connection by id")
    public void testFindById() {
        entityManager.persist(busConnection);
        entityManager.flush();

        BusConnection found = busConnectionRepository.findById(busConnection.getConId()).orElse(null);

        assertThat(found).isEqualTo(busConnection);
    }

    @Test
    @DisplayName("Test find bus connection by id with no results")
    public void testFindByIdNoResults() {
        entityManager.persist(busConnection);
        entityManager.flush();

        BusConnection found = busConnectionRepository.findById(2L).orElse(null);

        assertThat(found).isNull();
    }

    @Test
    @DisplayName("Test save bus connection")
    public void testSave() {
        BusConnection saved = busConnectionRepository.save(busConnection);

        assertThat(saved).isEqualTo(busConnection);
    }

    @Test
    @DisplayName("Test delete bus connection")
    public void testDelete() {
        entityManager.persist(busConnection);
        entityManager.flush();

        busConnectionRepository.delete(busConnection);

        assertThat(busConnectionRepository.findById(busConnection.getConId()).orElse(null)).isNull();
    }

    @Test
    @DisplayName("Test update bus connection")
    public void testUpdate() {
        entityManager.persist(busConnection);
        entityManager.flush();

        busConnection.setOrigin("Lisboa");
        busConnection.setDestination("Porto");
        busConnection.setDepartureDate("2021-06-02");
        busConnection.setDepartureTime("09:00:00");
        busConnection.setArrivalDate("2021-06-02");
        busConnection.setArrivalTime("11:20:00");
        busConnection.setPrice(15.0);
        busConnection.setTotalSeats(20);

        BusConnection updated = busConnectionRepository.save(busConnection);

        assertThat(updated).isEqualTo(busConnection);
    }

    @Test
    @DisplayName("Test update bus connection with no results")
    public void testUpdateNoResults() {
        entityManager.persist(busConnection);
        entityManager.flush();

        busConnection2.setOrigin("Lisboa");
        busConnection2.setDestination("Porto");
        busConnection2.setDepartureDate("2021-06-02");
        busConnection2.setDepartureTime("09:00:00");
        busConnection2.setArrivalDate("2021-06-02");
        busConnection2.setArrivalTime("11:20:00");
        busConnection2.setPrice(15.0);
        busConnection2.setTotalSeats(20);

        BusConnection updated = busConnectionRepository.save(busConnection2);

        assertThat(updated).isEqualTo(busConnection2);
    }

}
