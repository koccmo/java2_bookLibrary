package lv.javaguru.app.core.services.admin_side;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.admin.ShowUserRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.admin.ShowUserResponse;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShowUsersService {
	//private final Database database;
	private final UserDatabase userDatabase;

	public ShowUsersService (UserDatabase userDatabase) {
		this.userDatabase = userDatabase;
	}

	public ShowUserResponse<?> execute (ShowUserRequest request) {
		List<?> responseList = validate(request.getAdmin());

		if (!responseList.isEmpty()) {
			return new ShowUserResponse<>(responseList);
		}

			responseList = userDatabase.getAllUsers();

		return new ShowUserResponse<>(responseList);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
