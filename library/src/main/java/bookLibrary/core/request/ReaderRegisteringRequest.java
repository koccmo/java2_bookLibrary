package bookLibrary.core.request;

public class ReaderRegisteringRequest {
    private String name;
    private String lastName;

    private String personalCode;

    public ReaderRegisteringRequest(String name, String lastName, String personalCode) {
        this.name = name;
        this.lastName = lastName;
        this.personalCode = personalCode;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
