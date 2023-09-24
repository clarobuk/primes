package uk.co.clarob.primes.controller;

/**
 * Used when the maximumPossiblePrimeNumber is too small i.e. less than 2 or too large for the generator to handle
 * practically. Probably not worth creating a new exception for this, could use something like IllegalArgumentException,
 * but wanted to show an example of doing this.
 */
public class OutOfRangeException extends RuntimeException
{
    public OutOfRangeException(final String message)
    {
        super(message);
    }
}
