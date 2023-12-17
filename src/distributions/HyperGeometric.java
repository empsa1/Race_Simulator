package distributions;

public class HyperGeometric implements Distribution {

    public static int generateRandom(int n, int N, int K)
    {
        assert( 0 <= n && n <= N );
        assert( N >= 1 && K >= 0 );
        int count = 0;
        for ( int i = 0; i < n; i++, N-- ) {
            double p = (double) ( K ) / (double) ( N );
            if (Bernoulli.generateRandom(p)) {
                count++; K--;
            }
        }
        return count;
    }
}
