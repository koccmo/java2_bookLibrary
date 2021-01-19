package lv.javaguru.app.console_ui.admin_side.edit_user;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.admin.EditSurnameRequest;
import lv.javaguru.app.core.request.admin.EditUserNameRequest;
import lv.javaguru.app.core.response.admin.EditUserNameResponse;
import lv.javaguru.app.core.response.admin.EditUserSurnameResponse;
import lv.javaguru.app.core.services.admin_side.ManageUserService;

import java.util.Scanner;

public class EditUser_SecondName {
	private final User user;
	private ManageUserService manageUserService;

	public EditUser_SecondName (User user, ManageUserService manageUserService) {
		this.user = user;
		this.manageUserService = manageUserService;
	}

	public EditUser_SecondName (User user) {
		this.user = user;
	}

	public void execute (){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter new surname: ");
		String surname = scanner.nextLine();

		EditSurnameRequest request = new EditSurnameRequest(user, surname);
		EditUserSurnameResponse response = manageUserService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		} else
			System.out.println("surname updated");

	}
}
