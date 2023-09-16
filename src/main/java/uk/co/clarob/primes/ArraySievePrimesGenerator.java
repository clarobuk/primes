package uk.co.clarob.primes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An attempt to make the Sieve of Eratosthenes faster by using an array as working storage rather than a list. This
 * proved to be much faster and faster than SimplestPrimesGenerator.
 */
public class ArraySievePrimesGenerator implements PrimesGenerator
{
    @Override
    public List<Integer> generate(final int maximumPossibleNumber)
    {
        final boolean[] possibles = new boolean[maximumPossibleNumber + 1];
        Arrays.fill(possibles, true);
        possibles[0] = false;
        possibles[1] = false;
        int currentPrime = 2;
        while (currentPrime <= Math.sqrt(maximumPossibleNumber))
        {
            for (int toRemove = currentPrime * currentPrime; toRemove <= maximumPossibleNumber; toRemove += currentPrime)
            {
                possibles[toRemove] = false;
            }
            do
            {
                currentPrime++;
            }
            while (!possibles[currentPrime]);
        }
        final List<Integer> primes = new ArrayList<>();
        for (int index = 2; index <= maximumPossibleNumber; index++)
        {
            if (possibles[index])
            {
                primes.add(index);
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
