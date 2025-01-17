Feature: Validating place API

  @AddPlace
  Scenario Outline: verification if place is added using addPlace API
    Given add place payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with "POST" https request
    Then API call is success with status code 200
    Then "scope" in response body is "APP"
    Then "status" in response body is "OK"
    Then verify place id created maps to "<name>" using "getPlaceApi"

    Examples: 
      | name    | language | address  |
      | AAHouse | Fr-CA    | sjr blue |
     # | DDHouse | ES-es    | sjr black|
 
 
 @DeletePlace
   Scenario: verification if place is added using addPlace API
    Given delete place payload
    When user calls "DeletePlaceApi" with "POST" https request
    Then API call is success with status code 200
    Then "status" in response body is "OK"
 