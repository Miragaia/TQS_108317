// JavaScript code for trips.html

document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const origin = urlParams.get('origin');
    const destination = urlParams.get('destination');
    const departureDate = urlParams.get('departureDate');
    const storedData = localStorage.getItem('busConnections'); // Retrieve data from localStorage
    const data = storedData ? JSON.parse(storedData) : [];

    
    displayBusConnections(data);

    // Add event listener to handle button clicks
    const selectTripButtons = document.querySelectorAll(".selectTripButton");
    selectTripButtons.forEach(button => {
        button.addEventListener("click", function() {
            const tripId = button.dataset.tripId;
            // Redirect to reservation page with selected trip ID as query parameter
            window.location.href = `reservation.html?tripId=${tripId}`;
        });
    });
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
            <td>${connection.departureDate}</td>
            <td>${connection.arrivalDate}</td>
            <td>${connection.departureTime}</td>
            <td>${connection.arrivalTime}</td>
            <td>${connection.price}</td>
            <td>${connection.totalSeats}</td>
            <td><button class="selectTripButton" data-trip-id="${connection.conId}">Select Trip</button></td>
        `;
        tableBody.appendChild(row);
    });
}
