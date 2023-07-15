import java.io.FileWriter;
import java.io.IOException;

public class OutputFile {

    private final String filePath;

    public OutputFile(String filePath){
        this.filePath = filePath;
        createOutFile(this.filePath);
    }

    private static void createOutFile(String filename){
        FileWriter file;
        try {
            file = new FileWriter(filename,false);
            file.close();
        } catch (IOException e) {
        }
    }
    public <T> void writeln(T obj){
        write(obj +"\n");
    }

    public <T> void write(T obj){
        try {
            FileWriter outFile = new FileWriter(this.filePath, true);
            outFile.write(String.valueOf(obj));
            outFile.close();
        } catch (IOException e) {
        }
    }
}
