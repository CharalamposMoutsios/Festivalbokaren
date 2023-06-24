package festivalbokaren;
//import festivalbokaren.EventTicket;
//import festivalbokaren.TicketType;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketTest {
    @Test
    public void testGettersAndSetters() {
        // Create a ticket object
        EventTicket ticket = new EventTicket();

        // Set values using setters
        ticket.setFirstName("John");
        ticket.setLastName("Doe");
        ticket.setYearOfBirth(1990);
        ticket.setTicketType(TicketType.VIP);

        // Use getters to retrieve the values
        String firstName = ticket.getFirstName();
        String lastName = ticket.getLastName();
        int yearOfBirth = ticket.getYearOfBirth();
        TicketType ticketType = ticket.getTicketType();

        // Assert the retrieved values match the expected values
        Assertions.assertEquals("John", firstName);
        Assertions.assertEquals("Doe", lastName);
        Assertions.assertEquals(1990, yearOfBirth);
        Assertions.assertEquals(TicketType.VIP, ticketType);
    }

    @Test
    public void testInvalidFirstName() {
        // Create a ticket object
        EventTicket ticket = new EventTicket();

        // Set an invalid first name
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticket.setFirstName("John123"));
    }

    @Test
    public void testInvalidLastName() {
        // Create a ticket object
        EventTicket ticket = new EventTicket();

        // Set an invalid last name
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticket.setLastName(""));
    }

    @Test
    public void testInvalidYearOfBirth() {
        // Create a ticket object
        EventTicket ticket = new EventTicket();

        // Set an invalid year of birth
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticket.setYearOfBirth(2025));
    }
}
