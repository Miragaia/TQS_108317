// JavaScript code for trips.html

document.addEventListener("DOMContentLoaded", function() {
    // Add event listeners to all "Select Trip" buttons
    const selectButtons = document.querySelectorAll(".selectTripButton");
    selectButtons.forEach(button => {
        button.addEventListener("click", function(event) {
            event.preventDefault();
            // Get trip ID from data attribute
            const tripId = button.dataset.tripId;
            // Redirect to reservation.html with trip ID as query parameter
            window.location.href = `reservation.html?tripId=${tripId}`;
        });
    });
});
