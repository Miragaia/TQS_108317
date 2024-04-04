// JavaScript code for trips.html

document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const origin = urlParams.get('origin');
    const destination = urlParams.get('destination');
    const data = JSON.parse(urlParams.get('data'));

    // Display origin and destination
    document.getElementById("origin").innerText = origin;
    document.getElementById("destination").innerText = destination;

    // Display bus connections
    displayBusConnections(data);
});
