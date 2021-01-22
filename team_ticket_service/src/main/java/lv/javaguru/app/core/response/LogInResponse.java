package lv.javaguru.app.core.response;

import lv.javaguru.app.console_ui.modes.AdminMode;
import lv.javaguru.app.console_ui.modes.UserMode;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.User;

import java.util.List;

public class LogInResponse extends Response {
	private String message;
	private AdminMode adminMode;
	private UserMode userMode;
	private User currUser;


	public LogInResponse (String message) {
		this.message = message;
	}

	public LogInResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public LogInResponse (UserMode userMode) {
		this.userMode = userMode;
	}

	public LogInResponse (AdminMode adminMode) {
		this.adminMode = adminMode;
	}

	public String getMessage () {
		return message;
	}

	public void setMessage (String message) {
		this.message = message;
	}

	public User getCurrUser () {
		return currUser;
	}

	public void setCurrUser (User currUser) {
		this.currUser = currUser;
	}

	public boolean isAdminMode () {
		return adminMode != null;
	}

	public boolean isUserMode () {
		return userMode != null;
	}

	public AdminMode getAdminMode () {
		return adminMode;
	}

	public UserMode getUserMode () {
		return userMode;
	}
}
