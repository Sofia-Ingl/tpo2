import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {

    public static void main(String[] args) {
        ResultFunc res = new ResultFunc();
        try {
            Writer w = new FileWriter("src/main/resources/out/sysOut.csv");
            res.toCSV(-4, 7, 0.1, 0.00001, w);

            CosFunc res1 = new CosFunc();
            w = new FileWriter("src/main/resources/out/cosOut.csv");
            res1.toCSV(-Math.PI*2, Math.PI*2, 0.1, 0.00001, w);

            SinFunc res2 = new SinFunc();
            w = new FileWriter("src/main/resources/out/sinOut.csv");
            res2.toCSV(-Math.PI*2, Math.PI*2,0.1, 0.00001, w);

            TanFunc res6 = new TanFunc();
            w = new FileWriter("src/main/resources/out/tanOut.csv");
            res6.toCSV(-Math.PI+0.2, Math.PI-0.2, 0.1, 0.00001, w);

            CotFunc res3 = new CotFunc();
            w = new FileWriter("src/main/resources/out/cotOut.csv");
            res3.toCSV(-Math.PI+0.2, Math.PI-0.2, 0.1, 0.00001, w);

            CscFunc res4 = new CscFunc();
            w = new FileWriter("src/main/resources/out/cscOut.csv");
            res4.toCSV(-Math.PI+0.2, Math.PI-0.2, 0.1, 0.00001, w);

            SecFunc res5 = new SecFunc();
            w = new FileWriter("src/main/resources/out/secOut.csv");
            res5.toCSV(-Math.PI+0.1, Math.PI-0.1, 0.1, 0.00001, w);

            LnFunc res7 = new LnFunc();
            w = new FileWriter("src/main/resources/out/lnOut.csv");
            res7.toCSV(0.1, 5, 0.1, 0.00001, w);

            LogFunc res8 = new LogFunc();
            w = new FileWriter("src/main/resources/out/log3Out.csv");
            res8.toCSVModified(0.1, 5, 0.1, 3,0.00001, w);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
