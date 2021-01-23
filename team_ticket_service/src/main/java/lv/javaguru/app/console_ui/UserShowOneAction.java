package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowAllRequest;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.response.UserShowAllResponse;
import lv.javaguru.app.core.response.UserShowSingleResponse;
import lv.javaguru.app.core.services.UserShowSingleService;

public class UserShowOneAction {

	private final UserShowSingleService userShowSingleService;

	public UserShowOneAction (UserShowSingleService userShowSingleService) {
		this.userShowSingleService = userShowSingleService;
	}

	public void execute () {
		System.out.println("Enter user ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		UserShowSingleRequest request = new UserShowSingleRequest(id);
		UserShowSingleResponse response = userShowSingleService.execute(request);

		System.out.println(response.getUser());
	}
}
