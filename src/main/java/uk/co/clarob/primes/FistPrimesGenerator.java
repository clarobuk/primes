package uk.co.clarob.primes;

import java.util.ArrayList;
import java.util.List;

/**
 * First attempt to implement finding prime numbers without research, by putting all possibles into a list and then
 * removing those that are multiples of already established primes. Turns out this is a form of the Sieve of
 * Eratosthenes and is considerably slower than trial division using my code (SimplestPrimesGenerator).
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
