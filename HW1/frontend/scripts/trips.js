// JavaScript code for trips.html

document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const origin = urlParams.get('origin');
    const destination = urlParams.get('destination');
    const storedData = localStorage.getItem('busConnections'); // Retrieve data from localStorage
    const data = storedData ? JSON.parse(storedData) : [];

    // Filter bus connections based on origin and destination
    const filteredData = data.filter(connection => {
        return connection.origin === origin && connection.destination === destination;
    });

    // Display filtered bus connections
    displayBusConnections(filteredData);
});


// Function to display bus connections in the table
function displayBusConnections(busConnections) {
    const tableBody = document.getElementById("tripTableBody");
    tableBody.innerHTML = ""; // Clear existing rows
    
    busConnections.forEach(connection => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${connection.conId}</td>
            <td>${connection.origin}</td>
            <td>${connection.destination}</td>
            <td>${connection.departureTime}</td>
            <td>${connection.arrivalTime}</td>
            <td>${connection.price}</td>
            <td><button class="selectTripButton" data-trip-id="${connection.conId}">Select Trip</button></td>
        `;
        tableBody.appendChild(row);
    });
}
