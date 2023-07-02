package festivalbokaren;
/*
 * Represent an event ticket with information about the ticket provider.
 * 
 */
public class EventTicket {
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private TicketType ticketType;

    public EventTicket() {
        // Default constructor required for deserialization
    }

    /**
     * Constructs an EventTicket object with the specified information.
     *
     * @param firstName  the first name of the ticket holder
     * @param lastName   the last name of the ticket holder
     * @param yearOfBirth  the year of birth of the ticket holder
     * @param ticketType  the type of the ticket
     */


    public EventTicket(String firstName, String lastName, int yearOfBirth, TicketType ticketType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.ticketType = ticketType;
    }
    /* Returns the first namme of the ticket holder.
     * 
    */
    public String getFirstName() {
        return firstName;
    }

    /*  Sets the first name of the ticket holder.
    * exception if the first name contains numbers.
    * The regular expression ".*\\d.*"
    * is used to check if a string contains any digit (0-9).*/

    public void setFirstName(String firstName) {
        if (firstName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("First name cannot contain numbers.");
        }
        this.firstName = firstName;
    }

    // Returns the last name of the ticket holder.

    public String getLastName() {
        return lastName;
    }

    //Sets the last name of the ticket holder.
    //exception if the last name contains numbers.

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    // return the year of birth of the ticket holder.

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    // sets the year of birth of the ticket holder.
    //exception if the yearofbirth is not within the valid limits.
    public void setYearOfBirth(int yearOfBirth) throws InvalidYearOfBirthException {
        if (yearOfBirth < 1930 || yearOfBirth > 2023) {
            throw new InvalidYearOfBirthException("Year of birth must be between 1930 and 2023.");
        }
        this.yearOfBirth = yearOfBirth;
    }

    // return the type of the ticket.

    public TicketType getTicketType() {
        return ticketType;
    }

    // set the type of the ticket.

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }


    // returns a string representation of the EventTicket object.
    @Override
    public String toString() {
        return "Ticket{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", ticketType=" + ticketType +
                '}';
    }
}
