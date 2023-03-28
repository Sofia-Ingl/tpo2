public class LnFunc implements Function {

    public double calc(double x, double eps) {

        if (x < 0 || Double.isNaN(x)) {
            return Double.NaN;
        } else if (x == 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        // ln(x) = ln((t+1)/(t-1))
        double t = (x - 1)  / (x + 1);
        //ln((t+1)/(t-1)) = 2(t + t^3/3 + t^5/5 + ...)

        double sum = 0;
        double currentVal = t;
        int step = 1;
        while (Math.abs(currentVal) > eps / 2) {
            sum += currentVal;
            currentVal = (2 * step - 1) * currentVal * t*t / (2*step + 1);
            step++;
        }
        sum *= 2;
        return sum;

    }

}
