@testApi
Feature: BookerApi end to end check

  Background: Run ping health check
    Given Run ping health check after each request

  Scenario: Creating a new auth token using post request
    Given Create new auth token
  Scenario: Fetch all booking ids using get request
    Given Get all booking ids

  Scenario: Create a new booking and verify it
    Given Create a new booking
    Then Get details of the booking id

  Scenario: Update booking id and verify it
    Given Update booking id
    Then Get details of the booking id

  Scenario: Partially Update booking id and verify it
    Given Partially update booking id
    Then Get details of the booking id

  Scenario: Delete the booking
    Given Delete the booking id


