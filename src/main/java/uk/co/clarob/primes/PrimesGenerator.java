package uk.co.clarob.primes;

import java.util.List;

/**
 * All the different implementations of the prime numbers generator implement this interface. Given the specified
 * maximum it will generate all the prime numbers up to that maximum. It also has support ti indicate what is the
 * practical maximum this implementation can support.
 */
interface PrimesGenerator
{
    /**
     * Generate the list of prime numbers up to and possibly including the maximum specified.
     *
     * @param maximumPossiblePrimeNumber The maximum number that shoud be considered when generating prime numbers.
     * @return A list of prime numbers up to and possibly including the maximum specified.
     */
    List<Integer> generate(final int maximumPossiblePrimeNumber);

    /**
     * Each implementation has a practical limit to the size of the maximumPossiblePrimeNumber before the elapsed time
     * for the calculations is too great e.g. what can be done in about 10 seconds. This method returns that value, it
     * doesn't mean that the implementation won't calculate larger numbers but the caller should expect significant
     * delays.
     *
     * @return The practical limit to the size of the maximumPossiblePrimeNumber.
     */
    int practicalMaximum();
}
