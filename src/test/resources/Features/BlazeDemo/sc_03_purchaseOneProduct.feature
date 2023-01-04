Feature: Purchase One Product

  Background:
    Given User open browser and navigate to https://www.demoblaze.com/
    When Click on Log in where tag a
    When Enter value test1996@gmail.com where attributes id = loginusername
    When Enter value Test@1234 where attributes id = loginpassword
    When Click on Log in where tag button

  Scenario: User should be able to purchase at least one product
    When Click on Phones where tag a
    When Click on Nokia lumia 1520 where tag a
    When Click on Add to cart where tag a
    When Accept browser alert
    When Click on Cart where tag a
    When Click on Place Order where tag button
    When Enter value Areej where position 0 and attributes id = name
    When Enter value Jordan where position 0 and attributes id = country
    When Enter value Amman where position 0 and attributes id = city
    When Enter value 32323232232323 where position 0 and attributes id = card
    When Enter value 12 where position 0 and attributes id = month
    When Enter value 2022 where position 0 and attributes id = year
    When Click on Purchase where tag button
