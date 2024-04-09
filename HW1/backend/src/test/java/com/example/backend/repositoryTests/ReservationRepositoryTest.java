package com.example.backend.repositoryTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import com.example.backend.model.Reservation;
import com.example.backend.repository.ReservationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservationRepositoryTest {
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Test findByBusConId")
    public void testFindByBusConId() {
        Reservation reservation = new Reservation(1L, "John Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "John Doe", "12", "2023");
        entityManager.persist(reservation);
        entityManager.flush();

        List<Reservation> found = reservationRepository.findByBusConId(1L);

        assertThat(found).contains(reservation);

    }

    @Test
    @DisplayName("Test findByBusConId Not Found")
    public void testFindByBusConIdNotFound() {
        Reservation reservation = new Reservation(1L, "John Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "John Doe", "12", "2023");
        entityManager.persist(reservation);
        entityManager.flush();

        List<Reservation> found = reservationRepository.findByBusConId(2L);

        assertThat(found).isEmpty();

    }

    @Test
    @DisplayName("Test findByBusConId Multiple Reservations")
    public void testFindByBusConIdMultipleReservations() {
        Reservation reservation = new Reservation(1L, "John Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "John Doe", "12", "2023");
        Reservation reservation2 = new Reservation(1L, "Jane Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "Jane Doe", "12", "2023");
        entityManager.persist(reservation);
        entityManager.persist(reservation2);
        entityManager.flush();

        List<Reservation> found = reservationRepository.findByBusConId(1L);

        assertThat(found).contains(reservation, reservation2);

    }

    @Test
    @DisplayName("Test findByBusConId Multiple Reservations Different BusConId")
    public void testFindByBusConIdMultipleReservationsDifferentBusConId() {
        Reservation reservation = new Reservation(1L, "John Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "John Doe", "12", "2023");
        Reservation reservation2 = new Reservation(2L, "Jane Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "Jane Doe", "12", "2023");
        entityManager.persist(reservation);
        entityManager.persist(reservation2);
        entityManager.flush();

        List<Reservation> found = reservationRepository.findByBusConId(1L);

        assertThat(found).contains(reservation);

    }


    @Test
    @DisplayName("Test findByBusConId Multiple Reservations Different BusConId Not Found")
    public void testFindByBusConIdMultipleReservationsDifferentBusConIdNotFound() {
        Reservation reservation = new Reservation(1L, "John Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "John Doe", "12", "2023");
        Reservation reservation2 = new Reservation(2L, "Jane Doe", "Rua do Teste", "Aveiro", "Portugal", "3800-000", "1234567890123456", "Jane Doe", "12", "2023");
        entityManager.persist(reservation);
        entityManager.persist(reservation2);
        entityManager.flush();

        List<Reservation> found = reservationRepository.findByBusConId(3L);

        assertThat(found).isEmpty();

    }

}
