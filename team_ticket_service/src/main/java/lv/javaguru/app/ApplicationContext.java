package lv.javaguru.app;


import lv.javaguru.app.console_ui.*;
import lv.javaguru.app.console_ui.modes.UserMode;
import lv.javaguru.app.core.services.*;
import lv.javaguru.app.core.services.validators.*;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.FlightDatabase;
import lv.javaguru.app.database.UserDatabase;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

	private final Map<Class<?>, Object> beans = new HashMap<>();
	public static ApplicationContext instance = null;

	public static ApplicationContext getInstance () {
		if (instance == null)
			instance = new ApplicationContext();
		return instance;
	}

	private ApplicationContext () {
		beans.put(Database.class, new FlightDatabase());
		beans.put(UserDatabase.class, new UserDatabase());


		beans.put(AddUserRequestValidator.class, new AddUserRequestValidator());
		beans.put(EditUserValidator.class, new EditUserValidator());
		beans.put(AddFlightRequestValidator.class, new AddFlightRequestValidator());
		beans.put(EditFlightRequestValidator.class, new EditFlightRequestValidator());
		beans.put(LoginRequestValidator.class, new LoginRequestValidator());


		beans.put(FlightAddService.class, new FlightAddService(
				getBean(Database.class),
				getBean(AddFlightRequestValidator.class)
		));
		beans.put(FlightDeleteService.class, new FlightDeleteService(
				getBean(UserDatabase.class),
				getBean(Database.class)
		));
		beans.put(FlightEditService.class, new FlightEditService(
				getBean(UserDatabase.class),
				getBean(Database.class),
				getBean(EditFlightRequestValidator.class)
		));
		beans.put(FlightShowAllService.class, new FlightShowAllService(
				getBean(UserDatabase.class),
				getBean(Database.class)
		));
		beans.put(FlightShowOneService.class, new FlightShowOneService(
				getBean(UserDatabase.class),
				getBean(Database.class)
		));
		beans.put(LogInService.class, new LogInService(
				getBean(UserDatabase.class),
				getBean(Database.class),
				getBean(LoginRequestValidator.class)
		));
		beans.put(LogOutService.class, new LogOutService(
				getBean(UserDatabase.class)
		));
		beans.put(UserAddService.class, new UserAddService(
				getBean(UserDatabase.class),
				getBean(AddUserRequestValidator.class)
		));
		beans.put(UserDeleteService.class, new UserDeleteService(
				getBean(UserDatabase.class)
		));
		beans.put(UserEditService.class, new UserEditService(
				getBean(UserDatabase.class),
				getBean(EditUserValidator.class)
		));
		beans.put(UserShowAllService.class, new UserShowAllService(
				getBean(UserDatabase.class)
		));
		beans.put(UserShowSingleService.class, new UserShowSingleService(
				getBean(UserDatabase.class)
		));


		beans.put(ExitAction.class, new ExitAction());
		beans.put(FlightAddAction.class, new FlightAddAction(getBean(FlightAddService.class)));
		beans.put(FlightDeleteAction.class, new FlightDeleteAction(getBean(FlightDeleteService.class)));
		beans.put(FlightShowAllAction.class, new FlightShowAllAction(getBean(FlightShowAllService.class)));
		beans.put(FlightShowUsersAction.class, new FlightShowUsersAction(getBean(FlightShowOneService.class)));
		beans.put(FlightUpdateAction.class, new FlightUpdateAction(getBean(FlightEditService.class)));
		beans.put(LogInAction.class, new LogInAction(getBean(LogInService.class)));
		beans.put(LogOutAction.class, new LogOutAction(getBean(LogOutService.class)));

		beans.put(UserAddAction.class, new UserAddAction(getBean(UserAddService.class)));
		beans.put(UserDeleteAction.class, new UserDeleteAction(getBean(UserDeleteService.class)));
		beans.put(UserShowAllAction.class, new UserShowAllAction(getBean(UserShowAllService.class)));
		beans.put(UserShowOneAction.class, new UserShowOneAction(getBean(UserShowSingleService.class)));
		beans.put(UserUpdateAction.class, new UserUpdateAction(getBean(UserEditService.class)));

		/*beans.put(UserMode.class, new UserMode(
				getBean(UserDatabase.class),
				getBean(Database.class)
		));*/
	}

	public <T extends Object> T getBean (Class c) {
		return (T) beans.get(c);
	}

}