// package com.example.backend.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.Data;

// @Entity
// @Table(name = "seats")
// @Data
// public class Seat {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private int seatNumber;
//     private boolean isReserved;

//     public Seat() {
//     }

//     public Seat(int seatNumber, boolean isReserved) {
//         this.seatNumber = seatNumber;
//         this.isReserved = isReserved;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public int getSeatNumber() {
//         return seatNumber;
//     }

//     public void setSeatNumber(int seatNumber) {
//         this.seatNumber = seatNumber;
//     }

//     public boolean isReserved() {
//         return isReserved;
//     }

//     public void setReserved(boolean reserved) {
//         isReserved = reserved;
//     }

//     @Override
//     public String toString() {
//         return "Seat{" +
//                 "id=" + id +
//                 ", seatNumber=" + seatNumber +
//                 ", isReserved=" + isReserved +
//                 '}';
//     }
// }
