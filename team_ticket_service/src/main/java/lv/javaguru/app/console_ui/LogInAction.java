package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.modes.AdminMode;
import lv.javaguru.app.console_ui.modes.UserMode;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.services.LogInService;

public class LogInAction extends Action implements UIActions {

	private final LogInService loginService;


	public LogInAction (LogInService loginService) {
		this.loginService = loginService;
	}


	@Override
	public void execute () {
		User user = fillLoginForm();

		LogInRequest request = new LogInRequest(user);
		LogInResponse response = loginService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(System.out::println);
		}
		else {
			setLoggedInUser(response.getCurrUser());
			System.out.println(response.getMessage());
		}

		if (response.isAdminMode()) {
			AdminMode adminMode = response.getAdminMode();
			adminMode.printMainMenu(response.getCurrUser());
		}
		else if (response.isUserMode()) {
			UserMode userMode = response.getUserMode();
			userMode.printMainMenu(response.getCurrUser());
		}


	}


}
