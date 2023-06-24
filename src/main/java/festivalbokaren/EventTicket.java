package festivalbokaren;

public class EventTicket {
    private String firstName;
    private String lastName;
    private int yearOfBirth;
    private TicketType ticketType;

    public EventTicket() {
        // Default constructor required for deserialization
    }

    public EventTicket(String firstName, String lastName, int yearOfBirth, TicketType ticketType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.ticketType = ticketType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.matches(".*\\d.*")) {
            throw new IllegalArgumentException("First name cannot contain numbers.");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        this.lastName = lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) throws InvalidYearOfBirthException {
        if (yearOfBirth < 1930 || yearOfBirth > 2023) {
            throw new InvalidYearOfBirthException("Year of birth must be between 1930 and 2023.");
        }
        this.yearOfBirth = yearOfBirth;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

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
