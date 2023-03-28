import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class TanFunc implements Function {

    private SinFunc sin;
    private CosFunc cos;

    public TanFunc() {
        sin = new SinFunc();
        cos = new CosFunc(sin);
    }

    public TanFunc(SinFunc sinFunc, CosFunc cosFunc) {
        sin = sinFunc;
        cos = cosFunc;
    }

    public double calc(double x, double eps) {

        if (x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY || Double.isNaN(x)) {
            return Double.NaN;
        }

        double res = sin.calc(x, eps)/cos.calc(x, eps);
        if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        return res;
    }


}
