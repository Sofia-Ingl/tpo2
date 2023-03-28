import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class SecFunc implements Function {
    private CosFunc cos;

    public SecFunc() {
        cos = new CosFunc();
    }

    public SecFunc(CosFunc cosFunc) {
        cos = cosFunc;
    }
    public double calc(double x, double eps) {

        double cosRes = cos.calc(x, eps);
        if (Double.isNaN(cosRes) || cosRes == 0) return Double.NaN;
        return 1/cosRes;
    }


}
