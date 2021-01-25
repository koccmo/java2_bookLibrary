package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.request.LogOutRequest;
import lv.javaguru.app.core.response.LogOutResponse;
import lv.javaguru.app.core.services.LogOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogOutAction extends Action implements UIActions {
	@Autowired
	private LogOutService logoutService;


	@Override
	public void execute () {
		LogOutRequest request = new LogOutRequest(null);
		LogOutResponse response = logoutService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(System.out::println);
		}
		else {
			setLoggedInUser(response.getCurrUser());
			System.out.println(response.getMessage());
		}
	}
}
