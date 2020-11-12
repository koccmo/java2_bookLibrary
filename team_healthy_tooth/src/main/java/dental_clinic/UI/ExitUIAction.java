package dental_clinic.UI;

public class ExitUIAction implements  UIAction{

    @Override
    public void execute() {
        System.out.println(":) End of work day!");
        System.exit(0);
    }
}
