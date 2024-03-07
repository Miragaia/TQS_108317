package com.example.lab3_2cars;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.example.lab3_2cars.Controller.CarController;
import com.example.lab3_2cars.Model.Car;
import com.example.lab3_2cars.Service.CarService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(CarController.class)
public class CarsControllerTest {
    

    @Autowired
    private MockMvc mvc;


    @MockBean
    private CarService carService;


    @Test
    public void testCreateCar() throws Exception {
        Car car = new Car("Mercedes", "SLK200");

        when(carService.save(any())).thenReturn(car);

        mvc.perform(post("/api/newcar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.maker", is("Mercedes")));

        verify(carService, times(1)).save(any());
    }

    @Test
    public void testgetAllCars() throws Exception {
        Car car1 = new Car("Mercedes", "SLK200");
        Car car2 = new Car("Honda", "CRV");

        List<Car> cars = Arrays.asList(car1, car2);

        when(carService.getAllCars()).thenReturn(cars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].maker", is("Mercedes")))
        .andExpect(jsonPath("$[1].maker", is("Honda")));


        verify(carService, times(1)).getAllCars();
       
    }

    @Test 
    public void testGetCarById() throws Exception {
        Car car = new Car("Mercedes", "SLK200");
        car.setCarId(1L);

        when(carService.getCarDetails(1L)).thenReturn(car);

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maker", is("Mercedes")));

        verify(carService, times(1)).getCarDetails(1L);
    }

    @Test
    public void testGetCarByIdNotFound() throws Exception {
        when(carService.getCarDetails(1L)).thenReturn(null);

        mvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        verify(carService, times(1)).getCarDetails(1L);
    }
    


}
