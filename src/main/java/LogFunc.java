import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class LogFunc implements Function {

    private final LnFunc ln;

    public LogFunc() {
        this.ln = new LnFunc();
    }

    public LogFunc(LnFunc lnFunc) {
        this.ln = lnFunc;
    }

    public double calc(double x, double eps) {
        return ln.calc(x, eps);
    }

    public double calcModified(double x, double a, double eps) {
        return ln.calc(x, eps) / ln.calc(a, eps);
    }

    void toCSVModified(double from, double to, double deltaX, double a, double eps, Writer out) {

        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {

            for (double x = from; x <= to; x += deltaX) {
                double res = calcModified(x, a, eps);
                printer.printRecord(x, res);
            }

        } catch (IOException e) {
            System.out.println("Io exception");
        }
    }
}
