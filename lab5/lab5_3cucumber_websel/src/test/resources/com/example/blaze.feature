Feature: BlazeDemo

  Scenario: Flight Booking from Paris to Rome
    Given I navigate to "https://blazedemo.com/"
    When I select "Paris" for my departure City
    And I select "Rome" for my destination City
    And I click "Find Flights"
    Then I should be redirected to the page with title "BlazeDemo - reserve"
    When I click "Choose This Flight"
    Then I should be redirected to the page with title "BlazeDemo Purchase"
    And I enter my Name "Miguel Miragaia"
    And I enter my Address "123 Main St."
    And I enter my City "Viseu"
    And I enter my State "Viseu"
    And I enter my Zip Code 3515123
    And I enter my Credit Card Number 010101
    And I enter my Name on Card "Miguel Miragaia"
    And I click "Purchase Flight"
    Then I should be redirected to the page with title "BlazeDemo Confirmation"