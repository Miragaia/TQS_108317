// JavaScript code for reservation.html

document.addEventListener("DOMContentLoaded", function() {
    // Function to retrieve trip details from query parameter (replace this with your own method)
    function getTripDetailsFromQuery() {
        // Example: retrieve trip details from query parameter
        const params = new URLSearchParams(window.location.search);
        const tripId = params.get('tripId');
        // Use tripId to fetch trip details from your data source
        // For now, we'll use static fake trip details
        const tripDetails = {
            tripId: tripId,
            company: "Example Company",
            departureDate: "2024-03-25",
            departureTime: "10:00 AM",
            arrivalDate: "2024-03-25",
            arrivalTime: "1:00 PM",
            price: "$50"
        };
        return tripDetails;
    }

    // Function to display trip details on the page
    function displayTripDetails(tripDetails) {
        const tripDetailsContainer = document.getElementById('tripDetails');
        tripDetailsContainer.innerHTML = `
            <h2>Trip Details</h2>
            <p><strong>Trip ID:</strong> ${tripDetails.tripId}</p>
            <p><strong>Company:</strong> ${tripDetails.company}</p>
            <p><strong>Departure Date:</strong> ${tripDetails.departureDate}</p>
            <p><strong>Departure Time:</strong> ${tripDetails.departureTime}</p>
            <p><strong>Arrival Date:</strong> ${tripDetails.arrivalDate}</p>
            <p><strong>Arrival Time:</strong> ${tripDetails.arrivalTime}</p>
            <p><strong>Price:</strong> ${tripDetails.price}</p>
        `;
    }

    // Get trip details from query parameter
    const tripDetails = getTripDetailsFromQuery();

    // Display trip details on the page
    displayTripDetails(tripDetails);
});
