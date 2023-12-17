package distributions;

public class Geometric implements Distribution {

    public static int generateRandom(double p)
    {
        assert( 0. < p && p < 1. );
        return (int) (Math.log( Uniform.generateRandom( 0., 1. ) ) / Math.log( 1. - p ));
    }
}
