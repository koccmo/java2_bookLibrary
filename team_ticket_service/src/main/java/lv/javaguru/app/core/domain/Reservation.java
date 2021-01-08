package lv.javaguru.app.core.domain;

public class Reservation {
    private Person person;
    private Ticket ticket;

    public Reservation(Person person, Ticket ticket) {
        this.person = person;
        this.ticket = ticket;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
