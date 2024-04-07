document.addEventListener("DOMContentLoaded", function() {
    const searchForm = document.getElementById("searchForm");

    searchForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission

        const reservationId = document.getElementById("reservationId").value;

        // Send GET request to fetch reservation details
        fetch(`http://localhost:8080/api/reservations/${reservationId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Reservation not found');
                }
                return response.json();
            })
            .then(reservation => {
                displayReservationDetails(reservation);
            })
            .catch(error => {
                console.error('Error fetching reservation details:', error);
                displayErrorMessage("Reservation not found");
            });
    });

    function displayReservationDetails(reservation) {
        const searchResult = document.getElementById("searchResult");
        searchResult.innerHTML = `
            <h2>Reservation Details</h2>
            <p><strong>Reservation ID:</strong> ${reservation.resId}</p>
            <p><strong>Bus Connection ID:</strong> ${reservation.busConId}</p>
            <p><strong>Passenger Name:</strong> ${reservation.passengerName}</p>
            <p><strong>Address:</strong> ${reservation.address}</p>
            <p><strong>City:</strong> ${reservation.city}</p>
            <p><strong>Country:</strong> ${reservation.country}</p>
            <p><strong>Zip Code:</strong> ${reservation.zipCode}</p>
            <p><strong>Credit Card Number:</strong> ${reservation.creditCardNumber}</p>
            <p><strong>Card Holder Name:</strong> ${reservation.cardHolderName}</p>
            <p><strong>Card Expiration Month:</strong> ${reservation.cardExpirationMonth}</p>
            <p><strong>Card Expiration Year:</strong> ${reservation.cardExpirationYear}</p>
        `;

        fetchBusConnectionDetails(reservation.busConId);
    }

    function displayErrorMessage(message) {
        const searchResult = document.getElementById("searchResult");
        searchResult.innerHTML = `<p>${message}</p>`;
    }

    function fetchBusConnectionDetails(busConId) {
        fetch(`http://localhost:8080/api/bus-connections/${busConId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Bus Connection not found');
                }
                return response.json();
            })
            .then(busConnection => {
                displayBusConnectionDetails(busConnection);
            })
            .catch(error => {
                console.error('Error fetching bus connection details:', error);
                displayErrorMessage("Bus Connection not found");
            });
    }

    function displayBusConnectionDetails(busConnection) {
        const searchResult = document.getElementById("searchResultBus");
        searchResult.innerHTML += `
            <h2>Bus Connection Details</h2>
            <p><strong>Bus Connection ID:</strong> ${busConnection.conId}</p>
            <p><strong>Origin</strong> ${busConnection.origin}</p>
            <p><strong>Destination:</strong> ${busConnection.destination}</p>
            <p><strong>Departure Date:</strong> ${busConnection.departureDate}</p>
            <p><strong>Arrival Date:</strong> ${busConnection.arrivalDate}</p>
            <p><strong>Departure Time:</strong> ${busConnection.departureTime}</p>
            <p><strong>Arrival Time:</strong> ${busConnection.arrivalTime}</p>
            <p><strong>Price:</strong> ${busConnection.price}</p>
            <p><strong>Total Seats:</strong> ${busConnection.totalSeats}</p>
        `;
    }
});
