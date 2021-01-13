package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;

public class LogInRequest {
   // private final String firstName;
   // private final String secondName;
   private final Person user;

    public LogInRequest (Person user) {
        this.user = user;
    }

    public Person getUser () {
        return user;
    }

    //  public LogInRequest (String firstName, String secondName) {
  //      this.firstName = firstName;
  //      this.secondName = secondName;
  //  }

 //  public String getFirstName () {
 //      return firstName;
 //  }

 //  public String getSecondName () {
 //      return secondName;
 //  }
}
