// JavaScript code for search.html

// JavaScript code for search.html

document.getElementById("searchButton").addEventListener("click", function(event) {
    event.preventDefault();
    const origin = document.getElementById("origin").value;
    const destination = document.getElementById("destination").value;

    if (origin && destination) {
        // Make a GET request to the backend API to search for bus connections
        fetch(`http://localhost:8080/api/bus-connections?origin=${origin}&destination=${destination}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Redirect to trips.html with the retrieved data
                const queryParams = new URLSearchParams({
                    origin: origin,
                    destination: destination,
                    data: JSON.stringify(data)
                });
                window.location.href = `trips.html?${queryParams}`;
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    } else {
        alert("Please fill both departure and destination cities.");
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
