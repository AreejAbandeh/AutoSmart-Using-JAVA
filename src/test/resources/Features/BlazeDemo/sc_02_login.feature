Feature: Login Into BlazeDemo Portal

Background:
  Given User open browser and navigate to https://www.demoblaze.com/

  Scenario Outline: User should be able to login with valid credentials
    When Click on Log in where tag a
    When Enter value <username> where attributes id = loginusername
    When Enter value <password> where attributes id = loginpassword
    When Click on Log in where tag button

    Examples:
    |username|password|
    |test1996@gmail.com|Test@1234|
    |test19968@gmail.com|Test@12348|
