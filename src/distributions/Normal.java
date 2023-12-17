package distributions;

public class Normal implements Distribution {

    public static double generateRandom(double mu, double sigma) {
        assert(sigma > 0.);
        double p, p1, p2;
        do {
            p1 = Uniform.generateRandom(-1., 1.);
            p2 = Uniform.generateRandom(-1., 1.);
            p = p1 * p1 + p2 * p2;
        } while (p >= 1.);
        return mu + sigma * p1 * Math.sqrt(-2. * Math.log(p) / p);
    }
}
