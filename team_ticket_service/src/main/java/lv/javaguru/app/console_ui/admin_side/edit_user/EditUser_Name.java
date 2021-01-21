package lv.javaguru.app.console_ui.admin_side.edit_user;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.admin.EditUserNameRequest;
import lv.javaguru.app.core.response.admin.EditUserNameResponse;
import lv.javaguru.app.core.services.admin_side.ManageUserService;

import java.util.Scanner;

public class EditUser_Name {
	private User user;
	private  ManageUserService manageUserService;

	public EditUser_Name (User user, ManageUserService manageUserService) {
		this.user = user;
		this.manageUserService = manageUserService;
	}

	public EditUser_Name (User user) {
		this.user = user;
	}

	public void execute (){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter new name: ");
		String departure = scanner.nextLine();

		EditUserNameRequest editUserNameRequest = new EditUserNameRequest(user, departure);
		EditUserNameResponse editTicketDepartureResponse = manageUserService.execute(editUserNameRequest);

		if (editTicketDepartureResponse.hasErrors()) {
			editTicketDepartureResponse.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		} else
			System.out.println("Ticket departure city updated");

	}
}
