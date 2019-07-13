Feature: person crud operation

  Background:
    Given baseUri is http://localhost:8081

  Scenario: client makes call to GET a person by given id
    When the client calls /person/10
    Then the client receives status code of 200
    And response body should be valid json