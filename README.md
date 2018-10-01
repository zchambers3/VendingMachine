Program:
Vending Machine

Author:
Zack Chambers

Summary:
Final Project for CST-135

Date:
9/3/2018 - 10/21/2018

Description:
The vending machine will have two types of users – a customer, who will be able to select a product, and a BOSS, who will be able to maintain the collection of products in the machine. The machine itself will have a graphical interface that displays the available products and allows the user to make selections. The BOSS will have a separate interface that provides a variety of maintenance options. The machine will provide a means of alternating between the two types of users.

## Milestone 1
Sketch a storyboard of end product that shows the flow of the application (e.g., user input, application output).
Design a class using UML that models a product one might find in a snack machine. Submit UML for their product class. Submit a document with your UML.
Design a class using UML that models a dispenser of products. Students should submit UML for their dispenser class. Submit your UML as a document.

## Milestone 2
Part 1: Design an inheritance hierarchy using UML

Create and submit a design document that includes a UML for the following class hierarchy:

Product class
Drink class and Snack class (sub-classes of Product)
Candy class, Chips class and Gum class (sub-classes of Snack)
Your diagram should be developed using a tool such as Visio or MS Word and should illustrate the relationship between the classes.

Make sure that all classes include the following:

No argument constructor
Overloaded constructor that initializes the fields with the parameters
Overloaded copy constructor that initializes the fields with a copy of another object’s fields
A toString() method
You should add other methods you think are relevant to your class.

Part 2: Implementing the classes

Using your UML, write the code to implement the following classes:

Product (make this class abstract)
Snack (make this class abstract)
Drink
Candy
Chips
Gum
Dispenser (add a constructor that populates the Dispenser class array with a variety of Products)
Finally, write a main method that creates a Dispenser and calls the Dispenser method displayProducts().

## Milestone 3
Update your Snack and Drink classes so that they implement the Comparable interface. Comparison is based on the name of the item and follows alphabetical ordering rules, ignoring case. In other words, the Drink “Cola” is less than the Drink “Tea”. Items with the same name should be ordered on price in ascending order. If two items have the same name and price, they are considered equal. Write a main method that tests your compareTo() method. Submit your updated class files.

## Milestone 4
Use JavaFX to create the user interface for dispensing the product. You can design the user interaction as you see fit, but at the minimum the following components must be present:

1. A grid pane containing the images of the categories of available items (e.g., drinks, sweets, gum)
2. Each category should be associated with a clickable button.
3. Upon clicking the button, the display will change to show a different grid pane containing the available items in that category.
4. Each item should include a description and price.
5. Upon clicking the “done” button, the program will display, in a separate pane, the list of items, their price, and the total of the transaction.
