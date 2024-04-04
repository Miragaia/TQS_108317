package com.example.backend.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bus_connections")
@Data
public class BusConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long conId;
    
    @NotBlank(message = "Origin is required")
    private String origin;
    
    @NotBlank(message = "Destination is required")
    private String destination;

    @NotBlank(message = "Departure date is required")
    private String departureDate;

    @NotBlank(message = "Arrival date is required")
    private String arrivalDate;
    
    @NotBlank(message = "Departure time is required")
    private String departureTime;
    
    @NotBlank(message = "Arrival time is required")     //talvez tirar e arrival date tambem, preco tambem
    private String arrivalTime;

    private int totalSeats;
    
    @NotNull(message = "Price is required")
    private double price;

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "bus_connection_id")
    // private List<Seat> seats;


    // public BusConnection(String origin, String destination, String departureDate, String arrivalDate, String departureTime, String arrivalTime, double price, List<Seat> seats, int totalSeats) {
    //     this.origin = origin;
    //     this.destination = destination;
    //     this.departureDate = departureDate;
    //     this.arrivalDate = arrivalDate;
    //     this.departureTime = departureTime;
    //     this.arrivalTime = arrivalTime;
    //     this.price = price;
    //     this.seats = seats;
    //     this.totalSeats = totalSeats;
    // }

    public BusConnection(String origin, String destination, String departureDate, String arrivalDate, String departureTime, String arrivalTime, double price, int totalSeats) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.totalSeats = totalSeats;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
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

    //getters e setter dos seats
    // public List<Seat> getSeats() {
    //     return seats;
    // }

    // public void setSeats(List<Seat> seats) {
    //     this.seats = seats;
    // }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "BusConnection{" +
                "conId=" + conId +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", price=" + price +
                // ", seats=" + seats +
                '}';
    }
}
