// JavaScript code for trips.html
document.addEventListener("DOMContentLoaded", resetCurrency);
const storedData = localStorage.getItem('busConnections'); // Retrieve data from localStorage
const busConnections = storedData ? JSON.parse(storedData) : [];
let originalBaseCurrency = 'USD'; // Keep track of the original base currency


function resetCurrency() {
    const targetCurrencySelect = document.getElementById('targetCurrency');
    targetCurrencySelect.value = 'USD';
}

// Initialize base currency as USD
let baseCurrency = 'USD1';
let targetCurrency = '';

// Event listener for base currency selection
const targetCurrencySelect = document.getElementById('targetCurrency');

targetCurrencySelect.addEventListener('change', function() {

    if (baseCurrency === 'USD1') {
        baseCurrency = 'USD';
    }
    else {
        baseCurrency = targetCurrency;
    }

    // Update the base currency with the newly selected value
    targetCurrency = targetCurrencySelect.value;

    // Fetch exchange rates with the new base and target currencies
    fetchExchangeRates(baseCurrency, targetCurrency);
});


// Function to fetch exchange rates
function fetchExchangeRates(baseCurrency, targetCurrency) {
    const url = `http://localhost:8080/cache-exchange-rates/${baseCurrency}/${targetCurrency}`;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            // If the target currency changes, update the current target currency
            const exchangeRate = data;
            console.log('Bus connections:', busConnections);
            console.log('Exchange rate:', exchangeRate);
            console.log('Target currency:', targetCurrency);
            console.log('Base currency:', baseCurrency);
            busConnections.forEach(connection => {
                let priceInBaseCurrency;
                if (baseCurrency === 'USD') {
                    console.log('Price in base USD');
                    priceInBaseCurrency = parseFloat(connection.price);
                }
                else {
                    priceInBaseCurrency = parseFloat(connection.price);
                    console.log('Price in base currency:', priceInBaseCurrency);
                }
                const priceInTargetCurrency = priceInBaseCurrency * exchangeRate;
                console.log('Price in target currency:', priceInTargetCurrency);
                connection.price = priceInTargetCurrency.toFixed(2);
            });

            //display updated bus connections
            displayBusConnections(busConnections);

            // Reattach event listeners for "Select Trip" buttons
            attachEventListeners();
        })
        .catch(error => {       
            console.error('Error fetching exchange rates:', error);
        });
}

// Function to attach event listeners to "Select Trip" buttons
function attachEventListeners() {
    const selectTripButtons = document.querySelectorAll(".selectTripButton");
    selectTripButtons.forEach(button => {
        button.addEventListener("click", function() {
            const tripId = button.dataset.tripId;
            // Redirect to reservation page with selected trip ID as query parameter
            window.location.href = `reservation.html?tripId=${tripId}`;
        });
    });
}


document.addEventListener("DOMContentLoaded", function() {
    const urlParams = new URLSearchParams(window.location.search);
    const origin = urlParams.get('origin');
    const destination = urlParams.get('destination');
    const departureDate = urlParams.get('departureDate');
    const storedData = localStorage.getItem('busConnections'); // Retrieve data from localStorage
    const data = storedData ? JSON.parse(storedData) : [];

    
    displayBusConnections(data);

    // Add event listener to handle button clicks
    const selectTripButtons = document.querySelectorAll(".selectTripButton");
    selectTripButtons.forEach(button => {
        button.addEventListener("click", function() {
            const tripId = button.dataset.tripId;
            // Redirect to reservation page with selected trip ID as query parameter
            window.location.href = `reservation.html?tripId=${tripId}`;
        });
    });
});


// Function to display bus connections in the table
function displayBusConnections(busConnections) {
    const tableBody = document.getElementById("tripTableBody");
    tableBody.innerHTML = ""; // Clear existing rows
    
    busConnections.forEach(connection => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${connection.conId}</td>
            <td>${connection.origin}</td>
            <td>${connection.destination}</td>
            <td>${connection.departureDate}</td>
            <td>${connection.arrivalDate}</td>
            <td>${connection.departureTime}</td>
            <td>${connection.arrivalTime}</td>
            <td>${connection.price}</td>
            <td>${connection.totalSeats}</td>
            <td><button class="selectTripButton" data-trip-id="${connection.conId}">Select Trip</button></td>
        `;
        tableBody.appendChild(row);
    });
}
