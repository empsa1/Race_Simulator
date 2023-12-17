package distributions;

public class Binomial implements Distribution {

    public static int generateRandom(int n, double p) {
        assert (0. <= p && p <= 1. && n >= 1);
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (Bernoulli.generateRandom(p))
                sum += 1;
        }
        return sum;
    }
}