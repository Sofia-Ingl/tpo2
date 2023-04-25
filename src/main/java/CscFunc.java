public class CscFunc implements Function {
    private SinFunc sin;

    public CscFunc() {
        sin = new SinFunc();
    }

    public CscFunc(SinFunc sinFunc) {
        sin = sinFunc;
    }
    public double calc(double x, double eps) {

        double sinRes = sin.calc(x, eps);
        if (Double.isNaN(sinRes) || sinRes == 0) return Double.NaN;
        return 1/sinRes;
    }
}
