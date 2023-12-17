package distributions;

public class Pascal implements Distribution {

    public static int generateRandom( int s, double p )
    {
        return NegativeBinomial.generateRandom(s, p) + s;
    }
}
