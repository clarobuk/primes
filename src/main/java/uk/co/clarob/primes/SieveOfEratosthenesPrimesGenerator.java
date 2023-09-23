package uk.co.clarob.primes;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An attempt to make the Sieve of Eratosthenes faster by using an array as working storage rather than a list. This
 * proved to be much faster and faster than SimplestPrimesGenerator.
 */
@Component
public class SieveOfEratosthenesPrimesGenerator implements PrimesGenerator
{
    @Override
    public List<Integer> generate(final int maximumPossiblePrimeNumber)
    {
        final boolean[] possibles = new boolean[maximumPossiblePrimeNumber + 1];
        Arrays.fill(possibles, true);
        possibles[0] = false;
        possibles[1] = false;
        int currentPrime = 2;
        while (currentPrime <= Math.sqrt(maximumPossiblePrimeNumber))
        {
            for (int toRemove = currentPrime * currentPrime; toRemove <= maximumPossiblePrimeNumber; toRemove += currentPrime)
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
        for (int index = 2; index <= maximumPossiblePrimeNumber; index++)
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
        return 1000000000;
    }
}
