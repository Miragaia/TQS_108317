package com.example.lab3_2cars;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.lab3_2cars.Model.Car;
import com.example.lab3_2cars.Repository.CarRepository;

@DataJpaTest
public class CarRepositoryTest {
    

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private CarRepository carRepository;


    @Test
    public void testFindCarById(){
        Car car = new Car("Mercedes","SLK200");

        testEntityManager.persistAndFlush(car);

        Car foundCar = carRepository.findByCarId(car.getCarId());

        assertThat(foundCar).isNotNull();
        assertThat(foundCar.getCarId()).isEqualTo(car.getCarId());

        
    }

    @Test
    public void testFindCarByIdNull(){
        
        Car foundCar = carRepository.findByCarId(54L);

        assertThat(foundCar).isNull();
    }


    @Test
    public void testFindAllCars(){
        Car car1 = new Car("Mercedes","SLK200");
        Car car2 = new Car("Honda", "CRV");
        Car car3 = new Car ("Toyota", "Hilux");


        testEntityManager.persistAndFlush(car1);
        testEntityManager.persistAndFlush(car2);
        testEntityManager.persistAndFlush(car3);

        List<Car> cars = carRepository.findAll();


        assertThat(cars).isNotNull();
        assertThat(cars).hasSize(3).extracting(Car::getMaker).containsOnly(car1.getMaker(), car2.getMaker(), car3.getMaker());
    }
}
