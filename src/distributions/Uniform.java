package distributions;

public class Uniform implements Distribution {

    public static double generateRandom(double xMin, double xMax)
    {
        assert( xMin < xMax );
        return xMin + ( xMax - xMin ) * Math.random();
    }
}
