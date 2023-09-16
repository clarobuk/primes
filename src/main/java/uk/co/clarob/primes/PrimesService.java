package uk.co.clarob.primes;

import org.springframework.stereotype.Service;

/**
 * Service class: no real need for this problem, but shows standard structure, will do some validation here.
 */
@Service
public class PrimesService
{
    final PrimesGenerator primesGenerator = new SimplestPrimesGenerator();

    public PrimeNumbers getPrimesUpTo(final int maximumPossiblePrimeNumber)
    {
        return new PrimeNumbers(maximumPossiblePrimeNumber, primesGenerator.generate(maximumPossiblePrimeNumber));
    }
}
