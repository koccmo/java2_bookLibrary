package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.response.UserShowSingleResponse;
import lv.javaguru.app.core.services.UserShowSingleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserShowOneAction {

	@Autowired
	private UserShowSingleService userShowSingleService;


	public void execute () {
		System.out.println("Enter user ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		UserShowSingleRequest request = new UserShowSingleRequest(id);
		UserShowSingleResponse response = userShowSingleService.execute(request);

		System.out.println(response.getUser());
	}
}
