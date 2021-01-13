package lv.javaguru.app.core.domain;

public class Reservation {
    private Person person;
    private Ticket ticket;
    private Long id;

   // public Reservation(Ticket ticket) {
  //      this.ticket = ticket;
  //  }

    public Reservation(Person person, Ticket ticket) {
        this.person = person;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
