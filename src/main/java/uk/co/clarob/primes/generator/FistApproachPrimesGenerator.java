package uk.co.clarob.primes.generator;

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
        checkOutOfBounds(maximumPossiblePrimeNumber);
        // Create a list with all the possible prime numbers in it up to the maximum.
        final List<Integer> possibles = new ArrayList<>(maximumPossiblePrimeNumber);
        for (int number = 2; number <= maximumPossiblePrimeNumber; number++)
        {
            possibles.add(number);
        }

        int primeIndex = 0;
        // Keep indexing through the list until we reach the end, note the list gets shorter as we remove numbers we
        // find are not primes.
        while (primeIndex < possibles.size())
        {
            final int currentPrime = possibles.get(primeIndex);
            int index = primeIndex + 1;
            // Iterate through the list removing numbers that are multiples of the current prime
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
    public int getPracticalMaximum()
    {
        return 650000;
    }
}
