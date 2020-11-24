package online_schedule.core.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

    private PersonalData personalData;

    public Client(PersonalData personalData){
        this.personalData = personalData;

    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    List<Visit> visitList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(personalData, client.personalData) &&
                Objects.equals(visitList, client.visitList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalData, visitList);
    }

    @Override
    public String toString() {
        return "\nClient{" +
                personalData +
                ", visits=" + visitList +
                '}';
    }




}
