public class CosFunc implements Function {

    private final SinFunc sin;

    public CosFunc() {
        this.sin = new SinFunc();
    }

    public CosFunc(SinFunc sin) {
        this.sin = sin;
    }

    public double calc(double x, double eps) {

        if (x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY || Double.isNaN(x)) {
            return Double.NaN;
        }

        if (x >= 0) {
            int times = (int) (x/(Math.PI * 2));
            x -= times*Math.PI * 2;
        } else if (x < 0) {
            int times = (int) (Math.abs(x)/(Math.PI * 2));
            x += times*Math.PI * 2;
        }

        double result = Math.sqrt(1 - sin.calc(x, eps) * sin.calc(x, eps));
        if (((x > Math.PI / 2) && (x < 3 * Math.PI / 2)) || ((x < -Math.PI / 2) && (x > -3 * Math.PI / 2))) {
            result *= -1;
        }

        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) <= eps) return 0;

        return result;
    }
}
