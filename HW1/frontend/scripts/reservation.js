document.addEventListener("DOMContentLoaded", function() {

    const params = new URLSearchParams(window.location.search);
    const tripId = params.get('tripId');
    console.log(tripId);

    fetchTripDetails(tripId);

    function fetchTripDetails(tripId) {
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

                    if (totalReservations < totalSeats) {
                        createReservation(reservationData);
                    } else {
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
            console.log('Reservation created:', createdReservation);
            const reservationId = createdReservation.resId;
            window.location.href = `goodbyepage.html?reservationId=${reservationId}`;
        })
        .catch(error => {
            console.error('Error creating reservation:', error);
            alert('Failed to create reservation. Please try again.');
        });
    }

    const reservationForm = document.getElementById('reservationForm');
    reservationForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const busConId = tripId;

        const formData = new FormData(reservationForm);
        const reservationData = Object.fromEntries(formData.entries());

        reservationData.busConId = busConId;
        
        console.log(reservationData);

        fetchReservationsByBusConId(tripId , reservationData);
        
    });
});
