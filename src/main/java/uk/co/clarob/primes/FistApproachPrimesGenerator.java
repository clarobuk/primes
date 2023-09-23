package uk.co.clarob.primes;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * First attempt to implement finding prime numbers without research, by putting all possibles into a list and then
 * removing those that are multiples of already established primes. Turns out this is a form of the Sieve of
 * Eratosthenes and is considerably slower than trial division using my code (SimplestPrimesGenerator).
 */
@Component()
public class FistApproachPrimesGenerator implements PrimesGenerator
{
    @SuppressWarnings("java:s127")
    @Override
    @Cacheable("FistApproachPrimesGenerator")
    public List<Integer> generate(final int maximumPossiblePrimeNumber)
    {
        final List<Integer> possibles = new ArrayList<>(maximumPossiblePrimeNumber);
        for (int number = 2; number <= maximumPossiblePrimeNumber; number++)
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

    @Override
    public int practicalMaximum()
    {
        return 400000;
    }
}
