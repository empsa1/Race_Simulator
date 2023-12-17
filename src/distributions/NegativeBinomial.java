package distributions;

public class NegativeBinomial {

    public static int generateRandom( int s, double p )
    {
        assert( s >= 1 );
        int sum = 0;
        for ( int i = 0; i < s; i++ ) sum += Geometric.generateRandom( p );
        return sum;
    }
}
