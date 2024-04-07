document.addEventListener("DOMContentLoaded", function() {
    // Retrieve reservation ID from query parameter
    const params = new URLSearchParams(window.location.search);
    const reservationId = params.get('reservationId');

    // Display reservation ID on the page
    const reservationIdContainer = document.getElementById('reservationId');
    reservationIdContainer.textContent = `You have booked a trip with reservation ID: ${reservationId}`;

    const backButton = document.getElementById("backButton");
    backButton.addEventListener("click", function() {
        window.location.href = "search.html";
    });
});
