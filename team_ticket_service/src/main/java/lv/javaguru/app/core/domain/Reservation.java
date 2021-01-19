package lv.javaguru.app.core.domain;

public class Reservation {
    private User user;
    private Ticket ticket;
    private Long id;

   // public Reservation(Ticket ticket) {
  //      this.ticket = ticket;
  //  }

    public Reservation(User user, Ticket ticket) {
        this.user = user;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPerson() {
        return user;
    }

    public void setPerson(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
