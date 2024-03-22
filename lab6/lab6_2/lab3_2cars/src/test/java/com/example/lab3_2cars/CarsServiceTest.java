package com.example.lab3_2cars;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.lab3_2cars.Model.Car;
import com.example.lab3_2cars.Repository.CarRepository;
import com.example.lab3_2cars.Service.CarService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarsServiceTest {
    
    @Mock (lenient= true)
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;


    @BeforeEach
    public void setUp(){

        Car car = new Car("Mercedes","SLK200");
        Car car2 = new Car("Honda", "CRV");
        Car car3 = new Car ("Toyota", "Hilux");

        car.setCarId(1L);
        car2.setCarId(2L);
        car3.setCarId(3L);
        List<Car> allCars = List.of(car, car2, car3);

        when(carRepository.findAll()).thenReturn(allCars);
        when(carRepository.findByCarId(1L)).thenReturn(car);
        when(carRepository.findByCarId(2L)).thenReturn(car2);
        when(carRepository.findByCarId(3L)).thenReturn(car3);
        when(carRepository.findByCarId(54L)).thenReturn(null);
        
    }


    @Test
    public void testCarDetailsValid(){

        Car carToBeFound = carService.getCarDetails(1L);

        assertThat(carToBeFound.getMaker()).isEqualTo("Mercedes");

    }

    @Test
    public void testGetCarDetailsWrong(){
            
            Car carToBeFound = carService.getCarDetails(54L);
    
            assertThat(carToBeFound).isNull();
    }


    @Test
    public void testGetAllCars(){
            
            List<Car> cars = carService.getAllCars();
    
            assertThat(cars).hasSize(3).extracting(Car::getMaker).contains("Mercedes", "Honda", "Toyota");

            verify(carRepository).findAll();
    }

}
