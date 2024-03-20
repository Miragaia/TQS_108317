// JavaScript code for search.html

// Example function to handle form submission when the submit button is clicked
document.getElementById("searchButton").addEventListener("click", function(event) {
    event.preventDefault();
    const origin = document.getElementById("origin").value;
    const destination = document.getElementById("destination").value;

    // Check if both origin and destination are filled
    if (origin && destination) {
        // Redirect to trips.html with selected cities as query parameters
        window.location.href = `trips.html?origin=${origin}&destination=${destination}`;
    } else {
        // Alert user to fill both origin and destination fields
        alert("Please fill both departure and destination cities.");
    }
});
