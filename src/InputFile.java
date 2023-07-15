import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class InputFile {
    private final String filePath;

    public String getFilePath() {
        return filePath;
    }

    public InputFile(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Action> getActions(){
        ArrayList<Action> actions = new ArrayList<>();
        ArrayList<String> lines  = getLines();
        lines.forEach(line ->{
            ArrayList<String> splitedLine = splitLine(line);
            actions.add(new Action(
                    splitedLine.get(0),
                    new ArrayList<String>(splitedLine.subList(1,splitedLine.size()))
            ));
        });
        return actions;
    }

    public ArrayList<String> getLines(){
        try {
            return new ArrayList<String>(Files.readAllLines(Paths.get(this.filePath)));

        } catch (IOException e) {
            return null;
        }
    }

    private ArrayList<String> splitLine(String line){
        return new ArrayList<>(Arrays.asList(line.split("\t")));
    }

}
