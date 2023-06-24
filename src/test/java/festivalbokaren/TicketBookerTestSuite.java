package festivalbokaren;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// public class TicketBookerTestSuite {
//     @Test
//     public void testTicketCounts() {
//         TicketBooking ticketBooking = new TicketBooking();
//         int initialTicketCount = ticketBooking.getTicketCounts().get(TicketType.STANDARD);

//         ticketBooking.bookTicket();

//         int updatedTicketCount = ticketBooking.getTicketCounts().get(TicketType.STANDARD);
//         assertEquals(initialTicketCount - 1, updatedTicketCount, "Ticket count was not updated correctly");
//     }

    
// }


import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.Assert.fail;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class TicketBookerTestSuite {
    @Test
    public void testPrintSummary() {
        // Create an instance of TicketBooking
        TicketBooking ticketBooking = new TicketBooking();

        // Add some dummy bookings
        ticketBooking.getBookings().add(new EventTicket("John", "Doe", 1990, TicketType.STANDARD));
        ticketBooking.getBookings().add(new EventTicket("Jane", "Smith", 1985, TicketType.VIP));

        // Call the printSummary() method
        ticketBooking.printSummary();

        // TODO: Assert the output of the printSummary() method
        // You can capture the output using System.out.println() statements
        // or by redirecting the console output. Then, compare the captured output with the expected output using assertions.
        // For example, you can use assertEquals(expectedOutput, capturedOutput) to compare the two strings.
    }

    @Test
    public void testLoadBookings() {
        // Create an instance of TicketBooking
        TicketBooking ticketBooking = new TicketBooking();

        // Manually add some bookings to the list
        EventTicket[] bookings = {
            new EventTicket("John", "Doe", 1990, TicketType.VIP),
            new EventTicket("Jane", "Smith", 1985, TicketType.STANDARD),
            new EventTicket("Mike", "Johnson", 1995, TicketType.STANDARD_PLUS)
        };
        ticketBooking.getBookings().addAll(Arrays.asList(bookings));

        // Call the loadBookings() method
        ticketBooking.loadBookings();

        // Get the loaded bookings from the list
        List<EventTicket> loadedBookings = ticketBooking.getBookings();

        // Assert that the loaded bookings are equal to the manually added bookings
        assertEquals(bookings.length, loadedBookings.size(), "Loaded bookings count is not correct");
        for (int i = 0; i < bookings.length; i++) {
            EventTicket expectedBooking = bookings[i];
            EventTicket loadedBooking = loadedBookings.get(i);
            assertEquals(expectedBooking.getFirstName(), loadedBooking.getFirstName(), "First name is not correct");
            assertEquals(expectedBooking.getLastName(), loadedBooking.getLastName(), "Last name is not correct");
            assertEquals(expectedBooking.getYearOfBirth(), loadedBooking.getYearOfBirth(), "Year of birth is not correct");
            assertEquals(expectedBooking.getTicketType(), loadedBooking.getTicketType(), "Ticket type is not correct");
        }
    }

    @Test
public void testSaveBookings() {
    // Create an instance of TicketBooking
    TicketBooking ticketBooking = new TicketBooking();

    // Manually add some bookings to the list
    EventTicket[] bookings = {
        new EventTicket("John", "Doe", 1990, TicketType.VIP),
        new EventTicket("Jane", "Smith", 1985, TicketType.STANDARD),
        new EventTicket("Mike", "Johnson", 1995, TicketType.STANDARD_PLUS)
    };
    ticketBooking.getBookings().addAll(Arrays.asList(bookings));

    // Call the saveBookings() method
    ticketBooking.saveBookings();

    // Read the saved JSON file and parse the contents
    try {
        Path filePath = Path.of("Festivalbokaren/bookings.json");
        String json = Files.readString(filePath);
        EventTicket[] savedBookings = new ObjectMapper().readValue(json, EventTicket[].class);

        // Assert that the saved bookings are equal to the manually added bookings
        assertEquals(bookings.length, savedBookings.length, "Saved bookings count is not correct");
        for (int i = 0; i < bookings.length; i++) {
            EventTicket expectedBooking = bookings[i];
            EventTicket savedBooking = savedBookings[i];
            assertEquals(expectedBooking.getFirstName(), savedBooking.getFirstName(), "First name is not correct");
            assertEquals(expectedBooking.getLastName(), savedBooking.getLastName(), "Last name is not correct");
            assertEquals(expectedBooking.getYearOfBirth(), savedBooking.getYearOfBirth(), "Year of birth is not correct");
            assertEquals(expectedBooking.getTicketType(), savedBooking.getTicketType(), "Ticket type is not correct");
        }
    } catch (IOException e) {
        fail("Failed to read the saved bookings file");
    }
    }



}


