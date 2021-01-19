package lv.javaguru.app.console_ui.admin_side;

import lv.javaguru.app.console_ui.Action;
import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.console_ui.admin_side.edit_user.EditUser_Name;
import lv.javaguru.app.console_ui.admin_side.edit_user.EditUser_SecondName;
import lv.javaguru.app.console_ui.edit_ticket.EditTicket_Departure;
import lv.javaguru.app.console_ui.edit_ticket.EditTicket_Destination;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.EditTicketRequest;
import lv.javaguru.app.core.request.admin.ManageUserRequest;
import lv.javaguru.app.core.request.edit.EditTicketArrivalDateRequest;
import lv.javaguru.app.core.request.edit.EditTicketDepartureDateRequest;
import lv.javaguru.app.core.request.edit.EditTicketSeatRequest;
import lv.javaguru.app.core.response.EditTicketResponse;
import lv.javaguru.app.core.response.admin.ManageUserResponse;
import lv.javaguru.app.core.response.edit.EditTicketDepartureDateResponse;
import lv.javaguru.app.core.response.edit.EditTicketReturnDateResponse;
import lv.javaguru.app.core.response.edit.EditTicketSeatResponse;
import lv.javaguru.app.core.services.admin_side.ManageUserService;

import java.util.Scanner;

public class ManageUserAction extends Action implements UIActions {

	private final ManageUserService manageUserService;

	public ManageUserAction (ManageUserService manageUserService) {
		this.manageUserService = manageUserService;
	}

	private static void printAdminMode_manageUser (User currentUser) {
		BaseFunc.printHeader("", currentUser);
		BaseFunc.printLineSeparator();
		System.out.println(
				"[1] Change first name\n" +
						"[2] Change second name\n" +

						"[0] Back");
	}

	@Override
	public void execute () {
		System.out.println("Enter ticket ID: ");
		long id = BaseFunc.getMenuNumberFromUser();

		ManageUserRequest request = new ManageUserRequest(id);
		ManageUserResponse response = manageUserService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			adminMode_UserEditMenu(response.getUser());
	}

	private void adminMode_UserEditMenu (User user) {
		while (true) {
		printAdminMode_manageUser(user);
		int menuNumber = BaseFunc.getMenuNumberFromUser();
		switch (menuNumber) {
			case 1 -> {
				System.out.println("enter new name");
				EditUser_Name editUser_name = new EditUser_Name(user, manageUserService);
				editUser_name.execute();
			}
			case 2 -> {
				EditUser_SecondName editUser_SecondName = new EditUser_SecondName(user, manageUserService);
				editUser_SecondName.execute();
			}

			case 0 -> {
				return;
			}
		}

	}
}
}


