package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowAllRequest;
import lv.javaguru.app.core.response.UserShowAllResponse;
import lv.javaguru.app.core.services.UserShowAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserShowAllAction extends Action implements UIActions {

	@Autowired
	private UserShowAllService userShowAllService;


	@Override
	public void execute () {
		User admin = getLoggedInUser();

		UserShowAllRequest request = new UserShowAllRequest(admin);
		UserShowAllResponse<?> response = userShowAllService.execute(request);

		if (response.hasErrors()) {
			System.out.println("Error list:");
		}
		else {
			System.out.println("User list:");
		}

		response.printResponse();
	}


}
