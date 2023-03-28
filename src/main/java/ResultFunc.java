import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class ResultFunc implements Function {

    SinFunc sin;
    CosFunc cos;
    TanFunc tan;
    CotFunc cot;
    SecFunc sec;
    CscFunc csc;

    LnFunc ln;
    LogFunc log;

    public ResultFunc() {

        sin = new SinFunc();
        cos = new CosFunc(sin);
        tan = new TanFunc(sin, cos);
        cot = new CotFunc(sin, cos);
        sec = new SecFunc(cos);
        csc = new CscFunc(sin);

        ln = new LnFunc();
        log = new LogFunc(ln);
    }

    public ResultFunc(SinFunc sinFunc, CosFunc cosFunc,
                      SecFunc secFunc, CscFunc cscFunc,
                      TanFunc tanFunc, CotFunc cotFunc,
                      LnFunc lnFunc, LogFunc logFunc) {

        sin = sinFunc;
        cos = cosFunc;
        tan = tanFunc;
        cot = cotFunc;
        sec = secFunc;
        csc = cscFunc;

        ln = lnFunc;
        log = logFunc;
    }

    public double calc(double x, double eps) {

        double res = 0;
        if (x <= 0) {
            res = (((((((((((tan.calc(x, eps) / cos.calc(x, eps)) - cos.calc(x, eps)) + sec.calc(x, eps))
                    + (cot.calc(x, eps) + (csc.calc(x, eps) / csc.calc(x, eps)))) / cot.calc(x, eps))
                    - csc.calc(x, eps)) - cot.calc(x, eps)) + ((cot.calc(x, eps) / tan.calc(x, eps))
                    / sin.calc(x, eps))) - (((sec.calc(x, eps) - cot.calc(x, eps)) * (Math.pow(sec.calc(x, eps) , 2)))
                    / (csc.calc(x, eps) * (Math.pow((Math.pow(sec.calc(x, eps) , 3)) , 3))))) *
                    (Math.pow(((sin.calc(x, eps) * (tan.calc(x, eps) - cot.calc(x, eps))) *
                            (sin.calc(x, eps) - (csc.calc(x, eps) - sec.calc(x, eps)))) , 3)))
                    / ((sec.calc(x, eps) + sec.calc(x, eps)) + sin.calc(x, eps)));
        } else {
            res = ((((Math.pow((log.calcModified(x, 3, eps) * ln.calc(x, eps)) , 3)) - log.calcModified(x, 10, eps)) * ln.calc(x, eps))
                    - (Math.pow((log.calcModified(x, 5, eps) - (Math.pow(log.calcModified(x, 10, eps) , 2))) , 2)));
        }
        if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        return res;
    }

}
