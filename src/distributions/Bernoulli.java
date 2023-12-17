package distributions;

public class Bernoulli implements Distribution {

    public static boolean generateRandom(double p) {
            assert( 0. <= p && p <= 1. );
            return Uniform.generateRandom( 0., 1. ) < p;
    }
}
