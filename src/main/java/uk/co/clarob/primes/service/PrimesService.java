package uk.co.clarob.primes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import uk.co.clarob.primes.controller.OutOfRangeException;
import uk.co.clarob.primes.entity.PrimeNumbers;
import uk.co.clarob.primes.generator.PrimesGenerator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Service class: no real need for this problem, but shows standard structure, will do some validation here.
 */
@Service
public class PrimesService
{
    private static final Logger log = LoggerFactory.getLogger(PrimesService.class);

    private static final NumberFormat numberFormat = new DecimalFormat("#,###");

    private final PrimesGenerator firstApproachPrimesGenerator;
    private final PrimesGenerator trialDivisionPrimesGenerator;
    private final PrimesGenerator sieveOfEratosthenesPrimesGenerator;

    @Autowired
    public PrimesService(
            @Qualifier("fistApproachPrimesGenerator") final PrimesGenerator firstApproachPrimesGenerator,
            @Qualifier("trialDivisionPrimesGenerator") final PrimesGenerator trialDivisionPrimesGenerator,
            @Qualifier("sieveOfEratosthenesPrimesGenerator") final PrimesGenerator sieveOfEratosthenesPrimesGenerator)
    {
        this.firstApproachPrimesGenerator = firstApproachPrimesGenerator;
        this.trialDivisionPrimesGenerator = trialDivisionPrimesGenerator;
        this.sieveOfEratosthenesPrimesGenerator = sieveOfEratosthenesPrimesGenerator;
    }

    public PrimeNumbers getPrimesUsingFirstApproach(final int maximumPossiblePrimeNumber)
    {
        return validateAndCallGenerator(firstApproachPrimesGenerator, maximumPossiblePrimeNumber);
    }

    public PrimeNumbers getPrimesUsingTrialDivision(final int maximumPossiblePrimeNumber)
    {
        return validateAndCallGenerator(trialDivisionPrimesGenerator, maximumPossiblePrimeNumber);
    }

    public PrimeNumbers getPrimesUsingSieveOfEratosthenes(final int maximumPossiblePrimeNumber)
    {
        return validateAndCallGenerator(sieveOfEratosthenesPrimesGenerator, maximumPossiblePrimeNumber);
    }

    /**
     * Carry out validation and then run the calculations with a timer. There is also validation on the individual
     * generators, but just showing one technique here.
     *
     * @param primesGenerator The generator to use.
     * @param maximumPossiblePrimeNumber The maximum possible prime number.
     * @return PrimeNumbers entity with the maximum and a list of prime numbers calculated up to the maximum.
     */
    // java:S2629 - shouldn't evaluate parameters to logger in general as if logging at that level is switched off
    // then it is a waste of time, but we always want this logging so OK here
    @SuppressWarnings("java:S2629")
    private PrimeNumbers validateAndCallGenerator(
            final PrimesGenerator primesGenerator,
            final int maximumPossiblePrimeNumber)
    {
        if (maximumPossiblePrimeNumber < 2)
        {
            throw new OutOfRangeException(
                    "Maximum possible prime number (" + maximumPossiblePrimeNumber + ") provided is less than 2 which" +
                            " is not valid for generating prime numbers.");
        }
        if (maximumPossiblePrimeNumber > primesGenerator.getPracticalMaximum())
        {
            throw new OutOfRangeException(
                    "Maximum possible prime number (" + maximumPossiblePrimeNumber + ") provided is bigger than the" +
                            " practical maximum for the algorithm used by this prime number generator, there may be" +
                            " another algorithm on the API that will support this request.");
        }
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try
        {
            return new PrimeNumbers(maximumPossiblePrimeNumber, primesGenerator.generate(maximumPossiblePrimeNumber));
        }
        finally
        {
            stopWatch.stop();
            log.info("Finding all primes up to {} took {} millis",
                     numberFormat.format(maximumPossiblePrimeNumber),
                     (float) stopWatch.getLastTaskTimeNanos() / 1000000);
        }
    }
}
