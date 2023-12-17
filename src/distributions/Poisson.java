package distributions;

public class Poisson implements Distribution {

    public static int generateRandom( double mu )
    {
        assert( mu > 0. );
        double b = 1.;
        int i;
        for ( i = 0; b >= Math.exp( -mu ); i++ ) b *= Uniform.generateRandom( 0., 1. );
        return i - 1;
    }
}
