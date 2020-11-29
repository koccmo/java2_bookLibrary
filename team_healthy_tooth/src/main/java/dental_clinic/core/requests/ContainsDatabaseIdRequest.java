package dental_clinic.core.requests;


public class ContainsDatabaseIdRequest {

    private Long id;

    public ContainsDatabaseIdRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

}
