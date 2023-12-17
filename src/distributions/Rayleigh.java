package distributions;

public class Rayleigh implements Distribution {

    public static double generateRandom(double a, double b) {
        assert(b > 0.);
        return a + b * Math.sqrt(-Math.log(Uniform.generateRandom(0., 1.)));
    }
}
