package uk.co.clarob.primes.generator;

import uk.co.clarob.primes.controller.OutOfRangeException;

import java.util.List;

/**
 * All the different implementations of the prime numbers generator implement this interface. Given the specified
 * maximum it will generate all the prime numbers up to that maximum. It also has support ti indicate what is the
 * practical maximum this implementation can support.
 */
public interface PrimesGenerator
{
    /**
     * Generate the list of prime numbers up to and possibly including the maximum specified.
     *
     * @param maximumPossiblePrimeNumber The maximum number that should be considered when generating prime numbers.
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
    int getPracticalMaximum();

    /**
     * A method that can be used in each implementation to handle invalid inputs in a consistent way.
     * @param maximumPossiblePrimeNumber The maximum prime number requested.
     */
    default void checkOutOfBounds(final int maximumPossiblePrimeNumber)
    {
        if (maximumPossiblePrimeNumber < 2)
        {
            throw new OutOfRangeException(
                    "Maximum possible prime number (" + maximumPossiblePrimeNumber + ") provided is less than 2 which" +
                            " is not valid for generating prime numbers.");
        }
        if (maximumPossiblePrimeNumber > this.getPracticalMaximum())
        {
            throw new OutOfRangeException(
                    "Maximum possible prime number (" + maximumPossiblePrimeNumber + ") provided is bigger than the" +
                            " practical maximum for the algorithm used by this prime number generator, there may be" +
                            " another algorithm on the API that will support this request.");
        }
    }
}
