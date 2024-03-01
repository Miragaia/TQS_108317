# QUESTIONS (3.1) 

## a) Identify a couple of examples that use AssertJ expressive methods chaining.
* In A_EmployeeRepoTest.java
```java
   assertThat(found).isEqualTo(alex);
   assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

## b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).
* In B_EmployeeServiceTest.java
```java
   @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }

    @Test
    void whenSearchValidName_thenEmployeeShouldBeFound() {
        String name = "alex";
        Employee found = employeeService.getEmployeeByName(name);

        assertThat(found.getName()).isEqualTo(name);
    }
```

## c) What is the difference between standard @Mock and @MockBean?

* @Mock is used for basic united tests (only Mockito and JUnit)
* @MockBean is used for integration tests (Spring Boot tests). Is used to add mock objects to the Spring application context.

## d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
* 'application-integrationtest.properties' is a configuration file for Spring Boot integration tests, providing environment-specific properties. It's used to customize application behavior during integration testing by specifying properties like database connections or test-specific configurations.

## e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences? 
```
    -> Strategy C (Unit Testing):
        - Employing @WebMvcTest, this strategy isolates the web layer and mocks the service layer with @MockBean.
        - Its focus lies in examining the behavior of the web layer independently from the service layer and database, ensuring proper functioning of controller interactions.
        - This approach doesn't engage in actual database transactions.

    -> Strategy D (Integration Testing with MockMvc):
        - Leveraging @SpringBootTest, this approach loads the full application context.
        - By utilizing @AutoConfigureMockMvc, it injects a MockMvc instance to simulate HTTP requests.
        - The strategy replaces the live database with an in-memory database using @AutoConfigureTestDatabase, affirming the seamless interaction and integration between the web layer (controller) and the persistence layer (repository).

    -> Strategy E (Integration Testing with TestRestTemplate):
        - Similarly based on @SpringBootTest, this method initiates the server on a random port (WebEnvironment.RANDOM_PORT).
        - Employing TestRestTemplate, it engages with the server as a client, mimicking realistic HTTP interactions.
        - The strategy also adopts @AutoConfigureTestDatabase to swap out the actual database with an in-memory, offering a holistic end-to-end evaluation by emulating client-server dynamics.
```