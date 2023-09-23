package uk.co.clarob.primes.generator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * An attempt to make the Sieve of Eratosthenes faster by using an array as working storage rather than a list. This
 * proved to be faster and much faster than SimplestPrimesGenerator.
 */
@Component
public class SieveOfEratosthenesPrimesGenerator implements PrimesGenerator
{
    @Override
    public List<Integer> generate(final int maximumPossiblePrimeNumber)
    {
        checkOutOfBounds(maximumPossiblePrimeNumber);
        final boolean[] possibles = initialiseArray(maximumPossiblePrimeNumber);
        int currentPrime = 2;
        // Starting at 2 take each prime number in turn up to the square root of the maximum as by this point we will
        // have covered all the smallest of the factors e.g. if maximum is 36 by the time we have used 6 we will have set
        // 2 X 18, 3 X 12, 4 X 9 and 6 X 6 by taking multiples of 2, 3, 4 & 6 no need to do 9, 12 & 18.
        while (currentPrime <= sqrt(maximumPossiblePrimeNumber))
        {
            // For each prime for each multiple set the entry in the possible array to false
            for (int toRemove = currentPrime * currentPrime; toRemove <= maximumPossiblePrimeNumber; toRemove += currentPrime)
            {
                possibles[toRemove] = false;
            }
            // increment the current prime by one until we find the next entry that is true, which will give us our
            // next prime number.
            do
            {
                currentPrime++;
            }
            while (!possibles[currentPrime]);
        }
        return collectPrimeNumbers(maximumPossiblePrimeNumber, possibles);
    }

    /**
     * Iterate through the array collecting all the numbers that are still set to true, which have now been calculated
     * to be prime numbers.
     *
     * @param maximumPossiblePrimeNumber The maximum number that should be considered when generating prime numbers.
     * @param possibles The working array where non-prime numbers have been set to false.
     * @return The list of prime numbers.
     */
    private static List<Integer> collectPrimeNumbers(final int maximumPossiblePrimeNumber, final boolean[] possibles)
    {
        final List<Integer> primes = new ArrayList<>();
        for (int index = 2; index <= maximumPossiblePrimeNumber; index++)
        {
            if (possibles[index])
            {
                primes.add(index);
            }
        }
        return primes;
    }

    /**
     * Create a boolean array where each integer can be indexed directly. This means it needs to be one larger than the
     * value of the maximum i.e. if maximum is 10 we get and array of size 11, indexes 0->10 so that if we want to
     * exclude 10 from the set of possibles we simply need to go possibles[10] = false. Making elements zero and one
     * false is not really necessary as they are not considered when collecting the results into a list, but is
     * strictly correct as zero and one are not primes.
     *
     * @param maximumPossiblePrimeNumber The maximum number that should be considered when generating prime numbers.
     * @return The initialised array.
     */
    private static boolean[] initialiseArray(final int maximumPossiblePrimeNumber)
    {
        final boolean[] possibles = new boolean[maximumPossiblePrimeNumber + 1];
        Arrays.fill(possibles, true);
        possibles[0] = false;
        possibles[1] = false;
        return possibles;
    }

    @Override
    public int practicalMaximum()
    {
        return 1000000000;
    }
}
