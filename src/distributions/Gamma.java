package distributions;

import java.util.Random;

public class Gamma implements Distribution {
    private static Random random = new Random();

    public static double generateRandom(double a, double b, double c) {
        assert (b > 0. && c > 0.);
        final double A = 1. / Math.sqrt(2. * c - 1.);
        final double B = c - Math.log(4.);
        final double Q = c + 1. / A;
        final double T = 4.5;
        final double D = 1. + Math.log(T);
        final double C = 1. + c / Math.E;

        if (c < 1.) {
            while (true) {
                double p = C * Math.random();
                if (p > 1.) {
                    double y = -Math.log((C - p) / c);
                    if (Math.random() <= Math.pow(y, c - 1.)) return a + b * y;
                } else {
                    double y = Math.pow(p, 1. / c);
                    if (Math.random() <= Math.exp(-y)) return a + b * y;
                }
            }
        } else if (c == 1.0) {
            return exponential(a, b);
        } else {
            while (true) {
                double p1 = Math.random();
                double p2 = Math.random();
                double v = A * Math.log(p1 / (1. - p1));
                double y = c * Math.exp(v);
                double z = p1 * p1 * p2;
                double w = B + Q * v - y;
                if (w + D - T * z >= 0. || w >= Math.log(z)) return a + b * y;
            }
        }
    }

    private static double exponential(double a, double b) {
        return a - b * Math.log(random.nextDouble());
    }
}
