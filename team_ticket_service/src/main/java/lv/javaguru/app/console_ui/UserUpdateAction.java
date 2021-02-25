package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserEditRequest;
import lv.javaguru.app.core.response.UserEditResponse;
import lv.javaguru.app.core.services.UserEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserUpdateAction extends Action implements UIActions {

	@Autowired
	private UserEditService userEditService;


	private static void adminMode_printEditUser (User currentUser) {
		BaseFunc.printHeader("EDIT USER:", currentUser.getUsername() + ", " + currentUser.getPassword());
		System.out.println(
				"[1] Edit first name\n" +
						"[2] Edit second name\n" +

						"[0] Cancel");
	}

	@Override
	public void execute () {
		BaseFunc.printHeader("Enter user ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		UserEditRequest request = new UserEditRequest(id);
		UserEditResponse response = userEditService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			adminMode_UserEditMenu(response.getUser());
	}

	private void adminMode_UserEditMenu (User user) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			adminMode_printEditUser(user);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> editNameAction(user, scanner);
				case 2 -> editSurnameAction(user, scanner);

				case 0 -> {
					return;
				}
			}
		}
	}


	private void editSurnameAction (User user, Scanner scanner) {
		BaseFunc.printHeader("Enter new surname:");
		String surname = scanner.nextLine();

		UserEditRequest request = new UserEditRequest(user.getId(), surname);
		UserEditResponse response = userEditService.executeSurnameUpdate(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println(response.getMessage());
	}


	private void editNameAction (User user, Scanner scanner) {
		BaseFunc.printHeader("Enter new name:");
		String name = scanner.nextLine();

		UserEditRequest request = new UserEditRequest(user.getId(), name);
		UserEditResponse response = userEditService.executeNameUpdate(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println(response.getMessage());
	}
}


