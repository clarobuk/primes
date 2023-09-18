package uk.co.clarob.primes;

import org.springframework.stereotype.Service;

/**
 * Service class: no real need for this problem, but shows standard structure, will do some validation here.
 */
@Service
public class PrimesService
{
    final PrimesGenerator primesGenerator = new FistPrimesGenerator();
    final PrimesGenerator trialDivisionPrimesGenerator = new TrialDivisionPrimesGenerator();
    final PrimesGenerator sieveOfEratosthenesPrimesGenerator = new SieveOfEratosthenesPrimesGenerator();

    public PrimeNumbers getPrimesUpTo(final int maximumPossiblePrimeNumber)
    {
        return new PrimeNumbers(maximumPossiblePrimeNumber, primesGenerator.generate(maximumPossiblePrimeNumber));
    }

    public PrimeNumbers getPrimesUsingTrialDivision(final int maximumPossiblePrimeNumber)
    {
        return new PrimeNumbers(maximumPossiblePrimeNumber, trialDivisionPrimesGenerator.generate(maximumPossiblePrimeNumber));
    }

    public PrimeNumbers getPrimesUsingSieveOfEratosthenes(final int maximumPossiblePrimeNumber)
    {
        return new PrimeNumbers(maximumPossiblePrimeNumber, sieveOfEratosthenesPrimesGenerator.generate(maximumPossiblePrimeNumber));
    }
}
