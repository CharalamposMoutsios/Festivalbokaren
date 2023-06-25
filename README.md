Name: Charalampos Moutsios / DevOps 2022 Java
# Festivalbokaren/Ticket System

Its a Java program that allows users to book tickets by writing their information and choose a ticket type.
You can create a booking , retrieve(load) an existing booking from a json file, save your booking for later use and even check the remaining summaries of the tickets types.

# Usage of this technologies
1. Java, Programming language
2. Maven, Build automation and dependency tool
3. Junit, Testing framework for unit testing
4. Jackson, Working with JSON data

# Features
- You can create a new booking by providing the firstname,lastname,year of birth and the ticket type.
- Load an existing ticket booking from a Json file, that you have already saved first from the program.
- I have added a few handle exceptions for invalid input data, such as invalid year of birth , or no integer allowing when entering your name or lastname
- Running unit tests to verify the systems correctness
  
# Getting started
> Start by running the TicketBooking.java file, you will immediately see the menu, you will see 5 options:
1. Book a ticket
2. Print summary of booked tickets
3. Load bookings
4. Save bookings
5. Exit

> Then you can start with option 1, where you can add a firstname,lastname and yearofbirth, then you will be asked to choose a ticket type out of 4 choices
 1. STANDARD
 2. STANDARD_PLUS
 3. VIP
 4. OFFICIAL

- After that the user is going back to the menu and the booking is saved. Then the user can print the summary of booked tickets to see the running booking, or can save the booking.
- The user can exit the program and run it again and check the option number 2 ,which it will be reset
- The user can now choose the option 3 ,Load bookings, so can retrieve the booking that has been saved from before.
- The user can check the booking by entering the option number 2 and check the saved bookings.

# Sources 
- For this project i used various resources from the internet, few books i already owned and asking for suggestion from friends also that have much bigger experience in Java than me so that provide me with some guidance and inspiration in order to create this project.


- The following tutorials, websites, and YouTube videos were particularly helpful, cannot recall exact links:
- Stackoverflow.com > Java menu creation
- youtube.com > Introduction to Json in Java
- youtube.com > Unit testing in Java with Junit
- Google.com > enough googling that my gave me a headache! =)
  

