// JavaScript code for search.html
const origincities = ["Viseu", "Aveiro", "Coimbra", "Lisboa", "Porto"];
const destinationcities = ["Viseu", "Aveiro", "Coimbra", "Lisboa", "Porto"];

// Populate select elements with city options
function populateCityOptions() {
    const originSelect = document.getElementById("origin");
    const destinationSelect = document.getElementById("destination");

    origincities.forEach(city => {
        const originOption = document.createElement("option");
        originOption.text = city;
        originSelect.add(originOption);
    });

    populateDestinationOptions();
}

// Populate destination select options based on selected origin city
function populateDestinationOptions() {
    const originSelect = document.getElementById("origin");
    const destinationSelect = document.getElementById("destination");

    const selectedOriginCity = originSelect.value;
    const filteredDestinationCities = destinationcities.filter(city => city !== selectedOriginCity);

    // Clear destination dropdown before adding filtered options
    destinationSelect.innerHTML = "";

    // Add default "Select destination" option
    const defaultOption = document.createElement("option");
    defaultOption.text = "Select destination";
    destinationSelect.add(defaultOption);

    filteredDestinationCities.forEach(city => {
        const destinationOption = document.createElement("option");
        destinationOption.text = city;
        destinationSelect.add(destinationOption);
    });
}

// Ensure origin and destination are different
function validateCities() {
    const originSelect = document.getElementById("origin");
    const destinationSelect = document.getElementById("destination");

    if (originSelect.value === destinationSelect.value) {
        alert("Origin and destination cannot be the same.");
        destinationSelect.value = ""; // Reset destination to empty
    }
}

// Event listener for select change
document.getElementById("origin").addEventListener("change", function() {
    populateDestinationOptions();
    validateCities();
});

document.getElementById("destination").addEventListener("change", function() {
    validateCities();
});

// Call the populateCityOptions function when the page loads
document.addEventListener("DOMContentLoaded", populateCityOptions);


document.getElementById("searchButton").addEventListener("click", function(event) {
    event.preventDefault();
    const origin = document.getElementById("origin").value;
    const destination = document.getElementById("destination").value;

    if (origin && destination && destination !== "Select destination") {
        // Make a GET request to the backend API to search for bus connections
        fetch(`http://localhost:8080/api/bus-connections?origin=${origin}&destination=${destination}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                localStorage.setItem('busConnections', JSON.stringify(data)); // Store data in localStorage
                // Redirect to trips.html
                window.location.href = `trips.html?origin=${origin}&destination=${destination}`;
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    } else {
        alert("Please select both departure and destination cities.");
    }
});



// Function to display bus connections in a table
function displayBusConnections(busConnections) {
    const tableBody = document.getElementById("busConnectionTableBody");
    tableBody.innerHTML = ""; // Clear existing rows
    
    busConnections.forEach(connection => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${connection.origin}</td>
            <td>${connection.destination}</td>
            <td>${connection.departureTime}</td>
            <td>${connection.arrivalTime}</td>
            <td>${connection.price}</td>
        `;
        tableBody.appendChild(row);
    });
}

// Function to fetch bus connections from the backend
function fetchBusConnections() {
    fetch('http://localhost:8080/api/bus-connections')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayBusConnections(data); // Call the displayBusConnections function with the fetched data
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

// Call the fetchBusConnections function to fetch and display bus connections when the page loads
document.addEventListener("DOMContentLoaded", fetchBusConnections);
