package internet_store.core.responses;

import java.util.List;

public class ChangeProductNameResponse extends CoreResponse {

    private boolean changeName;

    public ChangeProductNameResponse(boolean changeName) { this.changeName = changeName; }

    public ChangeProductNameResponse(List<CoreError> errors) { super(errors); }

    public boolean isChangeName() { return changeName; }
}
