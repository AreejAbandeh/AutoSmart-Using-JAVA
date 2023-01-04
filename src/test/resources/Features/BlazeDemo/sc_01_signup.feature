Feature: Signup For BlazeDemo Portal

  Background:
    Given User open browser and navigate to https://www.demoblaze.com/

  Scenario: User should be able to Signup with valid credentials
    When Click on Sign up where tag a
    When Enter value test1235@gmail.com where attributes id = sign-username
    When Enter value Test@1234 where attributes id = sign-password
    When Click on Sign up where tag button