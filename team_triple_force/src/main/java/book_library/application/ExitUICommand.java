package book_library.application;

public class ExitUICommand implements UICommand {

    @Override
    public void execute() {
        System.out.println("Thank Your! Good by!");
        System.exit(0);
    }
}
