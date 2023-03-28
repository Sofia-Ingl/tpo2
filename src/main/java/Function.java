import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public interface Function {

    double calc(double x, double eps);

    default void toCSV(double from, double to, double deltaX, double eps, Writer out) {

        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {

            for (double x = from; x <= to; x += deltaX) {
                double res = calc(x, eps);
                printer.printRecord(x, res);
            }

        } catch (IOException e) {
            System.out.println("Io exception");
        }
    }
}
