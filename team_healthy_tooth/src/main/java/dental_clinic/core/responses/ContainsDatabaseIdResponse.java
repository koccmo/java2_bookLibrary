package dental_clinic.core.responses;

import java.util.List;

public class ContainsDatabaseIdResponse extends CoreResponse {

    private Long id;

    public ContainsDatabaseIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public ContainsDatabaseIdResponse(Long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

}
