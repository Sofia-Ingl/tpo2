import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

    public static void main(String[] args) {
        ResultFunc res = new ResultFunc();
        try {
            Writer w = new FileWriter("src/main/resources/out/sysOut.csv");
            res.toCSV(-4, 7, 0.1, 0.00001, w);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
