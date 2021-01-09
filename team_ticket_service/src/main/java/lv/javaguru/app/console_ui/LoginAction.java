package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.request.LoginRequest;
import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.LoginResponse;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.services.LoginService;

import java.util.Scanner;

public class LoginAction implements UIActions{
    private final LoginService loginService;

    public LoginAction(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void execute() {
        System.out.println("LOGIN:" + lineSeparator());
        Person newUser = fillPerson();

        LoginRequest request = new LoginRequest(newUser);
        LoginResponse response = loginService.execute(request);

        if (response.hasErrors())
            response.getErrorList().forEach(System.out::println);
        else
            System.out.println("Hooray! You have logged in!");
    }

    private static String lineSeparator() {
        String newLine = "\n";
        return newLine.concat(new String(new char[50]).replaceAll("", "="));
    }

    private Person fillPerson() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter surname: ");
        String surname = scanner.nextLine();

        return new Person(name, surname);
    }
}
