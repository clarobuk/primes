package uk.co.clarob.primes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Used to establish the practical maximum prime number that can be calculated by a particular implementation. This
 * uses a blunt force approach to keep doubling the maximum until the elapsed time takes longer than the specified
 * limit. This is currently just run locally via main as this will only be approximate as it will be deployment
 * environment specific.
 */
public class FindPracticalMaximum
{
    private static final Logger log = LoggerFactory.getLogger(FindPracticalMaximum.class);
    private static final NumberFormat numberFormat = new DecimalFormat("#,###");

    /**
     * Find the practical maximum for the given inputs. This will be an approximation as the algorithm here just keeps
     * doubling the maximum until the elapsed time exceeds elapsedTimeLimitInMilliseconds and then takes the previous
     * value, which will be below the limit. In practice rounding up the value returned will be fine.
     *
     * @param primesGenerator The generator to use for the tests.
     * @param elapsedTimeLimitInMilliseconds The time in milliseconds to act as the limit above which no further
     *                                       timings will be taken.
     * @param maximumToAttempt A backstop maximum prime number not to attempt to go beyond. In practice this is
     *                         Integer.MAX_VALUE as int has been used throughout this implementation to hold primes.
     * @return The practical maximum prime number found.
     */
    public int find(final PrimesGenerator primesGenerator,
                    final int elapsedTimeLimitInMilliseconds,
                    final int maximumToAttempt)
    {
        final StopWatch stopWatch = new StopWatch();
        for (int maximumPrimeNumber = 10000; maximumPrimeNumber <= maximumToAttempt; maximumPrimeNumber = maximumPrimeNumber * 2)
        {
            stopWatch.start();
            primesGenerator.generate(maximumPrimeNumber);
            stopWatch.stop();
            final float elapsedTime = (float) stopWatch.getLastTaskTimeNanos() / 1000000;
            log.info("Finding prime numbers up to {} primes took {} millis", numberFormat.format(maximumPrimeNumber), elapsedTime);
            if (elapsedTime > elapsedTimeLimitInMilliseconds)
            {
                return maximumPrimeNumber / 2;
            }
        }
        return maximumToAttempt;
    }

    public static void main(final String... arguments)
    {
        final FindPracticalMaximum findPracticalMaximum = new FindPracticalMaximum();
        final int elapsedTimeLimitInMilliseconds = 10000;
        final int maximumPrimeNumberToAttempt = Integer.MAX_VALUE;
        final int firstImplementationMaximum = findPracticalMaximum.find(
                new FistApproachPrimesGenerator(),
                elapsedTimeLimitInMilliseconds,
                maximumPrimeNumberToAttempt);
        final int trialDivisionMaximum = findPracticalMaximum.find(
                new TrialDivisionPrimesGenerator(),
                elapsedTimeLimitInMilliseconds,
                maximumPrimeNumberToAttempt);
        final int sieveOfEratosthenesMaximum = findPracticalMaximum.find(
                new SieveOfEratosthenesPrimesGenerator(),
                elapsedTimeLimitInMilliseconds,
                maximumPrimeNumberToAttempt);
        System.out.println("firstImplementation: " + firstImplementationMaximum);
        System.out.println("      trialDivision: " + trialDivisionMaximum);
        System.out.println("sieveOfEratosthenes: " + sieveOfEratosthenesMaximum);
    }
}
