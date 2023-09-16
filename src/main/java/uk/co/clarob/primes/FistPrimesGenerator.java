package uk.co.clarob.primes;

import java.util.ArrayList;
import java.util.List;

/**
 * An attempt to implement the simplest way of finding prime numbers. Iterate through all possible integers from 2 to
 * the maximum and test each in turn to see whether it has any factors. This has been slightly optimised as once you
 * have got halfway through the possible factors there is no need to test any further as we would have already found
 * the other factor in the pair.
 */
public class FistPrimesGenerator implements PrimesGenerator
{
    @SuppressWarnings("java:s127")
    @Override
    public List<Integer> generate(final int maximumPossibleNumber)
    {
        final List<Integer> possibles = new ArrayList<>(maximumPossibleNumber);
        for (int number = 2; number <= maximumPossibleNumber; number++)
        {
            possibles.add(number);
        }
        int primeIndex = 0;
        while (primeIndex < possibles.size())
        {
            final int currentPrime = possibles.get(primeIndex);
            int index = primeIndex + 1;
            while (index < possibles.size())
            {
                final Integer toTry = possibles.get(index);
                if (toTry % currentPrime == 0)
                {
                    possibles.remove(index);
                }
                else
                {
                    index++; // only increment if we didn't just remove one
                }
            }
            primeIndex++;
        }
        return possibles;
    }
}
