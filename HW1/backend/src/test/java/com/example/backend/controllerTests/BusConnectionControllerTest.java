package com.example.backend.controllerTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.DisplayName;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.backend.controller.BusConnectionController;
import com.example.backend.model.BusConnection;
import com.example.backend.service.BusConnectionService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.MediaType;

// Static imports
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@WebMvcTest(BusConnectionController.class)
public class BusConnectionControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusConnectionService busConnectionService;

    @Test
    @DisplayName("Test get all bus connections")
    public void testGetAllBusConnections() throws Exception {
        // Given
        BusConnection busConnection1 = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);
        BusConnection busConnection2 = new BusConnection("Aveiro", "Viseu", "2024-04-29", "2024-04-29",  "16:00:00",  "17:35:00", 30.0, 20);
        List<BusConnection> busConnections = List.of(busConnection1, busConnection2);

        when(busConnectionService.getAllBusConnections()).thenReturn(busConnections);

        // When
        mockMvc.perform(get("/api/bus-connections")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].origin", is("Coimbra")))
            .andExpect(jsonPath("$[0].destination", is("Aveiro")))
            .andExpect(jsonPath("$[0].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].departureTime", is("13:00:00")))
            .andExpect(jsonPath("$[0].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$[0].price", is(100.0)))
            .andExpect(jsonPath("$[0].totalSeats", is(50)))
            .andExpect(jsonPath("$[1].origin", is("Aveiro")))
            .andExpect(jsonPath("$[1].destination", is("Viseu")))
            .andExpect(jsonPath("$[1].departureDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].departureTime", is("16:00:00")))
            .andExpect(jsonPath("$[1].arrivalDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].arrivalTime", is("17:35:00")))
            .andExpect(jsonPath("$[1].price", is(30.0)))
            .andExpect(jsonPath("$[1].totalSeats", is(20)));

        verify(busConnectionService, times(1)).getAllBusConnections();
    }

    @Test
    @DisplayName("Test get bus connections by origin")
    public void testGetBusConnectionsByOrigin() throws Exception {
        // Given
        BusConnection busConnection1 = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);
        BusConnection busConnection2 = new BusConnection("Coimbra", "Viseu", "2024-04-29", "2024-04-29",  "16:00:00",  "17:35:00", 30.0, 20);
        List<BusConnection> busConnections = List.of(busConnection1, busConnection2);

        when(busConnectionService.getBusConnectionsByOrigin("Coimbra")).thenReturn(busConnections);

        // When
        mockMvc.perform(get("/api/bus-connections/origin/Coimbra")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].origin", is("Coimbra")))
            .andExpect(jsonPath("$[0].destination", is("Aveiro")))
            .andExpect(jsonPath("$[0].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].departureTime", is("13:00:00")))
            .andExpect(jsonPath("$[0].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$[0].price", is(100.0)))
            .andExpect(jsonPath("$[0].totalSeats", is(50)))
            .andExpect(jsonPath("$[1].origin", is("Coimbra")))
            .andExpect(jsonPath("$[1].destination", is("Viseu")))
            .andExpect(jsonPath("$[1].departureDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].departureTime", is("16:00:00")))
            .andExpect(jsonPath("$[1].arrivalDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].arrivalTime", is("17:35:00")))
            .andExpect(jsonPath("$[1].price", is(30.0)))
            .andExpect(jsonPath("$[1].totalSeats", is(20)));

        verify(busConnectionService, times(1)).getBusConnectionsByOrigin("Coimbra");
    }

    @Test
    @DisplayName("Test get bus connections by destination")
    public void testGetBusConnectionsByDestination() throws Exception {
        // Given
        BusConnection busConnection1 = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);
        BusConnection busConnection2 = new BusConnection("Aveiro", "Viseu", "2024-04-29", "2024-04-29",  "16:00:00",  "17:35:00", 30.0, 20);
        List<BusConnection> busConnections = List.of(busConnection1, busConnection2);

        when(busConnectionService.getBusConnectionsByDestination("Aveiro")).thenReturn(busConnections);

        // When
        mockMvc.perform(get("/api/bus-connections/destination/Aveiro")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].origin", is("Coimbra")))
            .andExpect(jsonPath("$[0].destination", is("Aveiro")))
            .andExpect(jsonPath("$[0].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].departureTime", is("13:00:00")))
            .andExpect(jsonPath("$[0].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$[0].price", is(100.0)))
            .andExpect(jsonPath("$[0].totalSeats", is(50)))
            .andExpect(jsonPath("$[1].origin", is("Aveiro")))
            .andExpect(jsonPath("$[1].destination", is("Viseu")))
            .andExpect(jsonPath("$[1].departureDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].departureTime", is("16:00:00")))
            .andExpect(jsonPath("$[1].arrivalDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].arrivalTime", is("17:35:00")))
            .andExpect(jsonPath("$[1].price", is(30.0)))
            .andExpect(jsonPath("$[1].totalSeats", is(20)));
        
        verify(busConnectionService, times(1)).getBusConnectionsByDestination("Aveiro");
    }

    @Test
    @DisplayName("Test get bus connections by departure date")
    public void testGetBusConnectionsByDepartureDate() throws Exception {
        // Given
        BusConnection busConnection1 = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);
        BusConnection busConnection2 = new BusConnection("Aveiro", "Viseu", "2024-04-27", "2024-04-27",  "16:00:00",  "17:35:00", 30.0, 20);
        List<BusConnection> busConnections = List.of(busConnection1, busConnection2);

        when(busConnectionService.getBusConnectionsByDepartureDate("2024-04-27")).thenReturn(busConnections);

        // When
        mockMvc.perform(get("/api/bus-connections/departure-date/2024-04-27")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].origin", is("Coimbra")))
            .andExpect(jsonPath("$[0].destination", is("Aveiro")))
            .andExpect(jsonPath("$[0].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].departureTime", is("13:00:00")))
            .andExpect(jsonPath("$[0].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$[0].price", is(100.0)))
            .andExpect(jsonPath("$[0].totalSeats", is(50)))
            .andExpect(jsonPath("$[1].origin", is("Aveiro")))
            .andExpect(jsonPath("$[1].destination", is("Viseu")))
            .andExpect(jsonPath("$[1].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[1].departureTime", is("16:00:00")))
            .andExpect(jsonPath("$[1].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[1].arrivalTime", is("17:35:00")))
            .andExpect(jsonPath("$[1].price", is(30.0)))
            .andExpect(jsonPath("$[1].totalSeats", is(20)));

        verify(busConnectionService, times(1)).getBusConnectionsByDepartureDate("2024-04-27");
    }

    @Test
    @DisplayName("Test get bus connection by id")
    public void testGetBusConnectionById() throws Exception {
        // Given
        BusConnection busConnection = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);

        when(busConnectionService.getBusConnectionById(1L)).thenReturn(busConnection);

        // When
        mockMvc.perform(get("/api/bus-connections/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.origin", is("Coimbra")))
            .andExpect(jsonPath("$.destination", is("Aveiro")))
            .andExpect(jsonPath("$.departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$.departureTime", is("13:00:00")))
            .andExpect(jsonPath("$.arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$.arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$.price", is(100.0)))
            .andExpect(jsonPath("$.totalSeats", is(50)));

        verify(busConnectionService, times(1)).getBusConnectionById(1L);
    }

    @Test
    @DisplayName("Test get bus connections by origin and destination")
    public void testGetBusConnectionsByOriginAndDestination() throws Exception {
        // Given
        BusConnection busConnection1 = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);
        BusConnection busConnection2 = new BusConnection("Coimbra", "Aveiro", "2024-04-29", "2024-04-29",  "16:00:00",  "17:35:00", 30.0, 20);
        List<BusConnection> busConnections = List.of(busConnection1, busConnection2);

        when(busConnectionService.getBusConnectionsByOriginAndDestination("Coimbra", "Aveiro")).thenReturn(busConnections);

        // When
        mockMvc.perform(get("/api/bus-connections/origin/Coimbra/destination/Aveiro")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].origin", is("Coimbra")))
            .andExpect(jsonPath("$[0].destination", is("Aveiro")))
            .andExpect(jsonPath("$[0].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].departureTime", is("13:00:00")))
            .andExpect(jsonPath("$[0].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$[0].price", is(100.0)))
            .andExpect(jsonPath("$[0].totalSeats", is(50)))
            .andExpect(jsonPath("$[1].origin", is("Coimbra")))
            .andExpect(jsonPath("$[1].destination", is("Aveiro")))
            .andExpect(jsonPath("$[1].departureDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].departureTime", is("16:00:00")))
            .andExpect(jsonPath("$[1].arrivalDate", is("2024-04-29")))
            .andExpect(jsonPath("$[1].arrivalTime", is("17:35:00")))
            .andExpect(jsonPath("$[1].price", is(30.0)));
        
        verify(busConnectionService, times(1)).getBusConnectionsByOriginAndDestination("Coimbra", "Aveiro");

    }

    @Test
    @DisplayName("Test get bus connection by origin, destination, and departure date")
    public void testGetBusConnectionByOriginAndDestinationAndDepartureDate() throws Exception {
        // Given
        BusConnection busConnection = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);

        when(busConnectionService.getBusConnectionsByOriginAndDestinationAndDepartureDate("Coimbra", "Aveiro", "2024-04-27")).thenReturn(List.of(busConnection));

        // When
        mockMvc.perform(get("/api/bus-connections/Coimbra/Aveiro/2024-04-27")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].origin", is("Coimbra")))
            .andExpect(jsonPath("$[0].destination", is("Aveiro")))
            .andExpect(jsonPath("$[0].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].departureTime", is("13:00:00")))
            .andExpect(jsonPath("$[0].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$[0].price", is(100.0)))
            .andExpect(jsonPath("$[0].totalSeats", is(50)));

        verify(busConnectionService, times(1)).getBusConnectionsByOriginAndDestinationAndDepartureDate("Coimbra", "Aveiro", "2024-04-27");
    }

    @Test
    @DisplayName("Test add bus connection")
    public void testAddBusConnection() throws Exception {
        // Given
        BusConnection busConnection = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);

        when(busConnectionService.addBusConnection(busConnection)).thenReturn(busConnection);

        // When
        mockMvc.perform(post("/api/bus-connections/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"origin\": \"Coimbra\", \"destination\": \"Aveiro\", \"departureDate\": \"2024-04-27\", \"arrivalDate\": \"2024-04-27\", \"departureTime\": \"13:00:00\", \"arrivalTime\": \"14:35:00\", \"price\": 100.0, \"totalSeats\": 50 }"))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.origin", is("Coimbra")))
            .andExpect(jsonPath("$.destination", is("Aveiro")))
            .andExpect(jsonPath("$.departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$.departureTime", is("13:00:00")))
            .andExpect(jsonPath("$.arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$.arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$.price", is(100.0)))
            .andExpect(jsonPath("$.totalSeats", is(50)));

        verify(busConnectionService, times(1)).addBusConnection(busConnection);
    }

    @Test
    @DisplayName("Test add several bus connections")
    public void testAddSeveralBusConnections() throws Exception {

        // Given
        List<BusConnection> busConnections = new ArrayList<>();
        busConnections.add(new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50));
        busConnections.add(new BusConnection("Porto", "Lisbon", "2024-04-28", "2024-04-28", "10:00:00", "13:00:00", 80.0, 40));

        when(busConnectionService.saveAllBusConnections(busConnections)).thenReturn(busConnections);

        // When
        mockMvc.perform(post("/api/bus-connections/add-all")
            .contentType(MediaType.APPLICATION_JSON)
            .content(Arrays.asList(
                "{ \"origin\": \"Coimbra\", \"destination\": \"Aveiro\", \"departureDate\": \"2024-04-27\", \"arrivalDate\": \"2024-04-27\", \"departureTime\": \"13:00:00\", \"arrivalTime\": \"14:35:00\", \"price\": 100.0, \"totalSeats\": 50 }",
                "{ \"origin\": \"Porto\", \"destination\": \"Lisbon\", \"departureDate\": \"2024-04-28\", \"arrivalDate\": \"2024-04-28\", \"departureTime\": \"10:00:00\", \"arrivalTime\": \"13:00:00\", \"price\": 80.0, \"totalSeats\": 40 }"
            ).toString()))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].origin", is("Coimbra")))
            .andExpect(jsonPath("$[0].destination", is("Aveiro")))
            .andExpect(jsonPath("$[0].departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].departureTime", is("13:00:00")))
            .andExpect(jsonPath("$[0].arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$[0].arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$[0].price", is(100.0)))
            .andExpect(jsonPath("$[0].totalSeats", is(50)))
            .andExpect(jsonPath("$[1].origin", is("Porto")))
            .andExpect(jsonPath("$[1].destination", is("Lisbon")))
            .andExpect(jsonPath("$[1].departureDate", is("2024-04-28")))
            .andExpect(jsonPath("$[1].departureTime", is("10:00:00")))
            .andExpect(jsonPath("$[1].arrivalDate", is("2024-04-28")))
            .andExpect(jsonPath("$[1].arrivalTime", is("13:00:00")))
            .andExpect(jsonPath("$[1].price", is(80.0)))
            .andExpect(jsonPath("$[1].totalSeats", is(40)));
        
        verify(busConnectionService, times(1)).saveAllBusConnections(busConnections);
    }

    @Test
    @DisplayName("Test update bus connection")
    public void testUpdateBusConnection() throws Exception {
        // Given
        BusConnection initialBusConnection = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);
        BusConnection updatedBusConnection = new BusConnection("Coimbra", "Lisboa", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);

        // Save the initial bus connection
    when(busConnectionService.getBusConnectionById(1L)).thenReturn(initialBusConnection);

    // Mock the update operation
    when(busConnectionService.updateBusConnection(eq(1L), any(BusConnection.class))).thenReturn(updatedBusConnection);



        // When
        mockMvc.perform(put("/api/bus-connections/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"origin\": \"Coimbra\", \"destination\": \"Lisboa\", \"departureDate\": \"2024-04-27\", \"arrivalDate\": \"2024-04-27\", \"departureTime\": \"13:00:00\", \"arrivalTime\": \"14:35:00\", \"price\": 100.0, \"totalSeats\": 50 }"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.origin", is("Coimbra")))
            .andExpect(jsonPath("$.destination", is("Lisboa")))
            .andExpect(jsonPath("$.departureDate", is("2024-04-27")))
            .andExpect(jsonPath("$.departureTime", is("13:00:00")))
            .andExpect(jsonPath("$.arrivalDate", is("2024-04-27")))
            .andExpect(jsonPath("$.arrivalTime", is("14:35:00")))
            .andExpect(jsonPath("$.price", is(100.0)))
            .andExpect(jsonPath("$.totalSeats", is(50)));

            // Then
            verify(busConnectionService, times(1)).updateBusConnection(eq(1L), any(BusConnection.class));
            verify(busConnectionService, times(1)).getBusConnectionById(1L);
    }

    @Test
    @DisplayName("Test delete bus connection by Id")
    public void testDeleteBusConnectionById() throws Exception {
        // Given
        BusConnection busConnection = new BusConnection("Coimbra", "Aveiro", "2024-04-27", "2024-04-27", "13:00:00", "14:35:00", 100.0, 50);

        when(busConnectionService.getBusConnectionById(1L)).thenReturn(busConnection);

        // When
        mockMvc.perform(delete("/api/bus-connections/delete/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        verify(busConnectionService, times(1)).deleteBusConnection(1L);
    }

}
