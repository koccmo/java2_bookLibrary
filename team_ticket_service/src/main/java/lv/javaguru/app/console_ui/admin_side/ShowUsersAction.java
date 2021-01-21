package lv.javaguru.app.console_ui.admin_side;

import lv.javaguru.app.console_ui.Action;
import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.ShowTicketsRequest;
import lv.javaguru.app.core.request.admin.ShowUserRequest;
import lv.javaguru.app.core.response.ShowTicketResponse;
import lv.javaguru.app.core.response.admin.ShowUserResponse;
import lv.javaguru.app.core.services.admin_side.ShowUsersService;

public class ShowUsersAction extends Action implements UIActions {
	private final ShowUsersService showUsersService;

	public ShowUsersAction (ShowUsersService showUsersService) {
		this.showUsersService = showUsersService;
	}

	@Override
	public void execute () {
		User admin = getLoggedInUser();

		ShowUserRequest request = new ShowUserRequest(admin);
		ShowUserResponse<?> response = showUsersService.execute(request);

		if (response.hasErrors()) {
			System.out.println("Error list:");
		}
		else {
			System.out.println("User list:");
		}

		response.printResponse();
	}
}
