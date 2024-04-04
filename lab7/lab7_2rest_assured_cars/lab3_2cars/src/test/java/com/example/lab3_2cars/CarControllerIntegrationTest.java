package com.example.lab3_2cars;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.lab3_2cars.Controller.CarController;
import com.example.lab3_2cars.Model.Car;
import com.example.lab3_2cars.Service.CarService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarController.class)
public class CarControllerIntegrationTest {

    //@Autowired
    //private CarController carController;

    @Autowired
    private MockMvc mockMvc; // Inject MockMvc

    @MockBean
    private CarService carService;

    @BeforeEach
    public void setUp() {
        // Set the MockMvc instance that REST Assured should use
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void testGetAllCars() {
        // Mocking service response
        Car car1 = new Car("Toyota", "Corolla");
        Car car2 = new Car("Honda", "Accord");
        List<Car> cars = Arrays.asList(car1, car2);
        when(carService.getAllCars()).thenReturn(cars);

        // Perform GET request and validate response using RestAssured
        String responseBody =
            RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/api/cars")
            .then()
                .statusCode(200)
                .extract().asString(); // Extract the response body as a string

        System.out.println(responseBody); // Print out the response body

        // Assertion statements
        assertThat(responseBody, containsString("Toyota"));
        assertThat(responseBody, containsString("Honda"));
    }


    @Test 
    public void testGetCarById() {
        Car car = new Car("Mercedes", "SLK200");
        car.setCarId(1L);

        when(carService.getCarDetails(1L)).thenReturn(car);

        RestAssuredMockMvc.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .get("/api/cars/1")
        .then()
            .statusCode(200)
            .body("maker", equalTo("Mercedes"));

        verify(carService).getCarDetails(1L);
    }

    @Test
    public void testGetCarByIdNotFound() {
        when(carService.getCarDetails(1L)).thenReturn(null);

        RestAssuredMockMvc.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .get("/api/cars/1")
        .then()
            .statusCode(404);

        verify(carService).getCarDetails(1L);
    }

    @Test
    public void testCreateCar() {
        Car car = new Car("Mercedes", "SLK200");

        when(carService.save(any())).thenReturn(car);

        RestAssuredMockMvc.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(car)
        .when()
            .post("/api/newcar")
        .then()
            .statusCode(201)
            .body("maker", equalTo("Mercedes"));

        verify(carService).save(any());
    }
}
