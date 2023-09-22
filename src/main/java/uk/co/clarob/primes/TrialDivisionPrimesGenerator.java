package uk.co.clarob.primes;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * An attempt to implement the simplest way of finding prime numbers. Iterate through all possible integers from 2 to
 * the maximum and test each in turn to see whether it has any factors. This has been slightly optimised as once you
 * have got to the square root of the number being test there is  no need to test any further as we would have
 * already found the other factor in the pair. Turns out this is called Trial Division and is surprisingly fast.
 */
public class TrialDivisionPrimesGenerator implements PrimesGenerator
{
    @Override
    public List<Integer> generate(final int maximumPossiblePrimeNumber)
    {
        final List<Integer> primes = new ArrayList<>();
        for (int possible = 2; possible <= maximumPossiblePrimeNumber; possible++)
        {
            boolean isPrime = true;
            for (int toTest = 2; toTest <= sqrt(possible); toTest++)
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

    @Override
    public int practicalMaximum()
    {
        return 20000000;
    }
}
