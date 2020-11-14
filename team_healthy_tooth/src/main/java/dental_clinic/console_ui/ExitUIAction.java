package dental_clinic.console_ui;

public class ExitUIAction implements  UIAction{

    @Override
    public void execute() {
        System.out.println(":) End of work day!");
        System.exit(0);
    }
}
