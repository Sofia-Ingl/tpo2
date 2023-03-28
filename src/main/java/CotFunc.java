public class CotFunc implements Function{

    private SinFunc sin;
    private CosFunc cos;

    public CotFunc() {
        sin = new SinFunc();
        cos = new CosFunc(sin);
    }

    public CotFunc(SinFunc sinFunc, CosFunc cosFunc) {
        sin = sinFunc;
        cos = cosFunc;
    }

    public double calc(double x, double eps) {

        if (x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY || Double.isNaN(x)) {
            return Double.NaN;
        }

        double res = cos.calc(x, eps)/sin.calc(x, eps);
        if (res == Double.NEGATIVE_INFINITY || res == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        return res;
    }
}
