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

    function fetchReservationsByBusConId(busConId, reservationData) {
        // Make a request to your backend server to fetch reservations by bus connection ID
        // Replace 'your_backend_url' with the actual URL of your backend endpoint
        fetch(`http://localhost:8080/api/reservations/bus-connections/${busConId}/reservations`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to fetch reservations');
                }
                return response.json();
            })
            .then(reservations => {
                console.log('Reservations:', reservations);
                const totalReservations = reservations.length;
                console.log('Total reservations:', totalReservations);
                
                fetch(`http://localhost:8080/api/bus-connections/${busConId}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to fetch bus connection details');
                    }
                    return response.json();
                })
                .then(busConnection => {
                    const totalSeats = busConnection.totalSeats;
                    // Compare total reservations with total seats
                    if (totalReservations < totalSeats) {
                        // Proceed with creating a new reservation
                        createReservation(reservationData);
                    } else {
                        // Display a message indicating that no more reservations can be made
                        alert('No more reservations can be made for this trip. Please select another trip.');
                    }
                })
                .catch(error => {
                    console.error('Error fetching bus connection details:', error);
                });
            })
            .catch(error => {
                console.error('Error fetching reservations:', error);
            });
    }

    function createReservation(reservationData) {
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
            const reservationId = createdReservation.resId;
            // Redirect to goodbye page with reservation ID
            window.location.href = `goodbyepage.html?reservationId=${reservationId}`;
        })
        .catch(error => {
            // Handle error
            console.error('Error creating reservation:', error);
            alert('Failed to create reservation. Please try again.');
        });
    }


    // Add event listener for form submission
    const reservationForm = document.getElementById('reservationForm');
    reservationForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        const busConId = tripId;

        // Collect form data
        const formData = new FormData(reservationForm);
        const reservationData = Object.fromEntries(formData.entries());

        reservationData.busConId = busConId;
        
        console.log(reservationData);

        // Fetch reservations associated with the tripId
        fetchReservationsByBusConId(tripId , reservationData);

        
    });
});
