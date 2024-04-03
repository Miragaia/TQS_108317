package com.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


import org.junit.Test;



public class AppTest 
{
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/todos";

    @Test
    public void testGetTodos() {
        given().when().get(BASE_URL).then().assertThat().statusCode(200);
    }

    @Test
    public void testGetTodo4() {
        given().when().get(BASE_URL + "/4").then().assertThat().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void testGetTodosId() {
        given().when().get(BASE_URL).then().assertThat().statusCode(200).body("id", hasItems(198, 199));
    }

    @Test
    public void testGetTodosLessThan2sec() {
        given().when().get(BASE_URL).then().assertThat().statusCode(200).time(lessThan(2000L));
    }
}
