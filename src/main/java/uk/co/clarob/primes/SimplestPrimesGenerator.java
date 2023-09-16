package uk.co.clarob.primes;

import java.util.ArrayList;
import java.util.List;

/**
 * An attempt to implement the simplest way of finding prime numbers. Iterate through all possible integers from 2 to
 * the maximum and test each in turn to see whether it has any factors. This has been slightly optimised as once you
 * have got halfway through the possible factors there is no need to test any further as we would have already found
 * the other factor in the pair.
 */
public class SimplestPrimesGenerator implements PrimesGenerator
{
    @Override
    public List<Integer> generate(final int maximumPossibleNumber)
    {
        final List<Integer> primes = new ArrayList<>();
        for (int possible = 2; possible <= maximumPossibleNumber; possible++)
        {
            boolean isPrime = true;
            for (int toTest = 2; toTest <= possible / 2; toTest++)
            {
                if (possible % toTest == 0)
                {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
            {
                primes.add(possible);
            }
        }
        return primes;
    }
}
