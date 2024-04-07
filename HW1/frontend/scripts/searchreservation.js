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
    }

    function displayErrorMessage(message) {
        const searchResult = document.getElementById("searchResult");
        searchResult.innerHTML = `<p>${message}</p>`;
    }
});
