package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resId;
    
    private Long busConId;
    private String passengerName;
    private String address;
    private String city;
    private String country;
    private String zipCode;
    private String creditCardNumber;
    private String cardHolderName;
    private String cardExpirationMonth;
    private String cardExpirationYear;
    
    public Reservation() {
    }

    public Reservation(Long busConId , String passengerName, String address, String city, String country, String zipCode, String creditCardNumber, String cardHolderName, String cardExpirationMonth, String cardExpirationYear) {
        this.passengerName = passengerName;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.creditCardNumber = creditCardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpirationMonth = cardExpirationMonth;
        this.cardExpirationYear = cardExpirationYear;
        this.busConId = busConId;
    }

    public Long getId() {
        return resId;
    }

    public void setId(Long resId) {
        this.resId = resId;
    }

    public Long getBusConId() {
        return busConId;
    }

    public void setBusConId(Long busConId) {
        this.busConId = busConId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardExpirationMonth() {
        return cardExpirationMonth;
    }

    public void setCardExpirationMonth(String cardExpirationMonth) {
        this.cardExpirationMonth = cardExpirationMonth;
    }

    public String getCardExpirationYear() {
        return cardExpirationYear;
    }

    public void setCardExpirationYear(String cardExpirationYear) {
        this.cardExpirationYear = cardExpirationYear;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + resId +
                ", busConId=" + busConId +
                ", passengerName='" + passengerName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardExpirationMonth='" + cardExpirationMonth + '\'' +
                ", cardExpirationYear='" + cardExpirationYear + '\'' +
                '}';
    }
}
