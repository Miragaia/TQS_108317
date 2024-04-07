document.addEventListener("DOMContentLoaded", function() {

    // Retrieve trip ID from query parameter
    const params = new URLSearchParams(window.location.search);
    const tripId = params.get('tripId');
    console.log(tripId);

    // Fetch trip details using the trip ID
    fetchTripDetails(tripId);

    // Function to retrieve trip details from the backend server using the trip ID
    function fetchTripDetails(tripId) {
        // Make a request to your backend server to fetch trip details
        // Replace 'your_backend_url' with the actual URL of your backend endpoint
        fetch(`http://localhost:8080/api/bus-connections/${tripId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch trip details');
                }
                return response.json();
            })
            .then(tripDetails => {
                displayTripDetails(tripDetails);
            })
            .catch(error => {
                console.error('Error fetching trip details:', error);
            });
    }

    // Function to display trip details on the page
    function displayTripDetails(tripDetails) {
        const tripDetailsContainer = document.getElementById('tripDetails');
        tripDetailsContainer.innerHTML = `
            <h2>Trip Details</h2>
            <p><strong>Trip ID:</strong> ${tripDetails.conId}</p>
            <p><strong>Origin:</strong> ${tripDetails.origin}</p>
            <p><strong>Destination:</strong> ${tripDetails.destination}</p>
            <p><strong>Departure Date:</strong> ${tripDetails.departureDate}</p>
            <p><strong>Departure Time:</strong> ${tripDetails.departureTime}</p>
            <p><strong>Arrival Date:</strong> ${tripDetails.arrivalDate}</p>
            <p><strong>Arrival Time:</strong> ${tripDetails.arrivalTime}</p>
            <p><strong>Price:</strong> ${tripDetails.price}</p>
            <p><strong>Total Seats:</strong> ${tripDetails.totalSeats}</p>
        `;
    }

    // Add event listener for form submission
    const reservationForm = document.getElementById('reservationForm');
    reservationForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        // Collect form data
        const formData = new FormData(reservationForm);
        const reservationData = Object.fromEntries(formData.entries());
        console.log(reservationData);

        // Send POST request to create reservation
        fetch(`http://localhost:8080/api/reservations/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reservationData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to create reservation');
            }
            return response.json();
        })
        .then(createdReservation => {
            // Handle success
            console.log('Reservation created:', createdReservation);
            alert('Reservation created successfully!');
        })
        .catch(error => {
            // Handle error
            console.error('Error creating reservation:', error);
            alert('Failed to create reservation. Please try again.');
        });
    });
});
