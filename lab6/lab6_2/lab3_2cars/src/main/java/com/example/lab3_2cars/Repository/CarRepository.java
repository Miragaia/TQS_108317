package com.example.lab3_2cars.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab3_2cars.Model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    public Car findByCarId(Long carId);
    
    public List<Car> findAll();
}