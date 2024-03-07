package com.example.lab3_2cars.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lab3_2cars.Model.Car;
import com.example.lab3_2cars.Repository.CarRepository;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarDetails(Long id) {
        return carRepository.findByCarId(id);
    }
}