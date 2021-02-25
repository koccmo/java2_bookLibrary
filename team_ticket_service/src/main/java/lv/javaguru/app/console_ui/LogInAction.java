package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.modes.AdminMode;
import lv.javaguru.app.console_ui.modes.UserMode;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.services.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LogInAction extends Action implements UIActions {

	@Autowired
	private LogInService loginService;


	@Override
	public void execute () {
		User user = fillLoginForm();

		LogInRequest request = new LogInRequest(user.getUsername(), user.getPassword());
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
