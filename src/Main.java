import java.util.ArrayList;
import java.util.Optional;

public class Main {
    public static void main(String[] args){
        InputFile inputFile = new InputFile(args[0]);
        OutputFile outputFile = new OutputFile(args[1]);
        ArrayList<Action> actions = inputFile.getActions();

        Library library = new Library();
        LibraryController controller = new LibraryController(library);

        actions.forEach(action -> {
            try {
                Optional<?> response = controller.actionHandler(action);
                response.ifPresent(outputFile::writeln);
            } catch (ControllerExceptions e) {
                outputFile.writeln(e.getCause().getMessage());
            }
        });

    }
}
