package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "bus_connections")
@Data
public class BusConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conId;
    
    @NotBlank(message = "Origin is required")
    @Size(max = 100, message = "Origin must be at most 100 characters")
    private String origin;
    
    @NotBlank(message = "Destination is required")
    @Size(max = 100, message = "Destination must be at most 100 characters")
    private String destination;
    
    @NotBlank(message = "Departure time is required")
    private String departureTime;
    
    @NotBlank(message = "Arrival time is required")
    private String arrivalTime;
    
    @NotNull(message = "Price is required")
    private double price;
    

    public BusConnection(String origin, String destination, String departureTime, String arrivalTime, double price) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public BusConnection() {
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BusConnection{" +
                "conId=" + conId +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", price=" + price +
                '}';
    }
}
