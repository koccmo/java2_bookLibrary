package dental_clinic.console_ui;

import dental_clinic.dependency_injection.DIComponent;

@DIComponent
public class ExitUIAction implements  UIAction{

    @Override
    public void execute() {
        System.out.println(":) End of work day!");
        System.exit(0);
    }
}
