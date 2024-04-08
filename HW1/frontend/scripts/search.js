
const origincities = ["Viseu", "Aveiro", "Coimbra", "Lisboa", "Porto"];
const destinationcities = ["Viseu", "Aveiro", "Coimbra", "Lisboa", "Porto"];

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

function populateDestinationOptions() {
    const originSelect = document.getElementById("origin");
    const destinationSelect = document.getElementById("destination");

    const selectedOriginCity = originSelect.value;
    const filteredDestinationCities = destinationcities.filter(city => city !== selectedOriginCity);

    destinationSelect.innerHTML = "";

    const defaultOption = document.createElement("option");
    defaultOption.text = "Select destination";
    destinationSelect.add(defaultOption);

    filteredDestinationCities.forEach(city => {
        const destinationOption = document.createElement("option");
        destinationOption.text = city;
        destinationSelect.add(destinationOption);
    });
}

function validateCities() {
    const originSelect = document.getElementById("origin");
    const destinationSelect = document.getElementById("destination");

    if (originSelect.value === destinationSelect.value) {
        alert("Origin and destination cannot be the same.");
        destinationSelect.value = "";
    }
}

document.getElementById("origin").addEventListener("change", function() {
    populateDestinationOptions();
    validateCities();
});

document.getElementById("destination").addEventListener("change", function() {
    validateCities();
});

document.addEventListener("DOMContentLoaded", populateCityOptions);


document.getElementById("searchButton").addEventListener("click", function(event) {
    event.preventDefault();
    const origin = document.getElementById("origin").value;
    const destination = document.getElementById("destination").value;
    const departureDate = document.getElementById("departureDate").value;

    if (origin && destination && destination !== "Select destination") {
        console.log("departureDate", !departureDate)
        if (departureDate) {
            fetch(`http://localhost:8080/api/bus-connections/${origin}/${destination}/${departureDate}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    if (data.length > 0) {
                        localStorage.setItem('busConnections', JSON.stringify(data));
                        window.location.href = `trips.html?origin=${origin}&destination=${destination}&departureDate=${departureDate}`;
                    } else {
                        console.log("No bus connections found.");
                        alert("No bus connections found for the selected criteria.");
                    }
                })
                .catch(error => {
                    console.error('There was a problem with the fetch operation:', error);
                });
        }
        else {
            alert("Please select a departure date.");
        }
    } else {
        alert("Please select both departure and destination cities.");
    }
});

function displayBusConnections(busConnections) {
    const tableBody = document.getElementById("busConnectionTableBody");
    tableBody.innerHTML = "";
    
    busConnections.forEach(connection => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${connection.origin}</td>
            <td>${connection.destination}</td>
            <td>${connection.departureDate}</td>
            <td>${connection.arrivalDate}</td>
            <td>${connection.departureTime}</td>
            <td>${connection.arrivalTime}</td>
            <td>${connection.price}</td>
            <td>${connection.totalSeats}</td>
        `;
        tableBody.appendChild(row);
    });
}

function fetchBusConnections() {
    fetch('http://localhost:8080/api/bus-connections')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayBusConnections(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

document.addEventListener("DOMContentLoaded", fetchBusConnections);