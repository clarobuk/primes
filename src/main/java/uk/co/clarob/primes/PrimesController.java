package uk.co.clarob.primes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PrimesController
{
    private static final Logger log = LoggerFactory.getLogger(PrimesController.class);

    public static final String FIRST_APPROACH_LABEL = "Primes using first approach";
    public static final String TRIAL_DIVISION_LABEL = "Primes using Trial Division";
    public static final String SIEVE_OF_ERATOSTHENES_LABEL = "Primes using Sieve Of Eratosthenes";

    private final PrimesService primesService;

    @Autowired
    public PrimesController(final PrimesService primesService)
    {
        this.primesService = primesService;
        log.info("{} created", PrimesController.class.getSimpleName());
    }

    @GetMapping("/")
    public EntityModel<Usage> information()
    {
        log.info("information()");
        return EntityModel.of(
                new Usage("Service to generate and return prime numbers up to the specified maximum value"),
                linkTo(methodOn(PrimesController.class).getPrimes(10))
                        .withRel("Primes using default approach"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingFirstApproach(10))
                        .withRel(FIRST_APPROACH_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(10))
                        .withRel(TRIAL_DIVISION_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(10))
                        .withRel(SIEVE_OF_ERATOSTHENES_LABEL));

    }

    @GetMapping("/primes/{max}")
    public PrimeNumbers getPrimes(@PathVariable("max") final int maximumPossiblePrimeNumber)
    {
        log.info("getPrimes({})", maximumPossiblePrimeNumber);
        return primesService.getPrimes(maximumPossiblePrimeNumber);
    }

    @GetMapping("/primesUsingFirstApproach/{max}")
    public EntityModel<PrimeNumbers> getPrimesUsingFirstApproach(
            @PathVariable("max") final int maximumPossiblePrimeNumber)
    {
        log.info("getPrimesUsingFirstApproach({})", maximumPossiblePrimeNumber);
        final PrimeNumbers primes = primesService.getPrimes(maximumPossiblePrimeNumber);
        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimesUsingFirstApproach(maximumPossiblePrimeNumber))
                        .withSelfRel(),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPossiblePrimeNumber))
                        .withRel(TRIAL_DIVISION_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(maximumPossiblePrimeNumber))
                        .withRel(SIEVE_OF_ERATOSTHENES_LABEL));
    }

    @GetMapping("/primesUsingTrialDivision/{max}")
    public EntityModel<PrimeNumbers> getPrimesUsingTrialDivision(
            @PathVariable("max") final int maximumPossiblePrimeNumber)
    {
        log.info("getPrimesUsingTrialDivision({})", maximumPossiblePrimeNumber);
        final PrimeNumbers primes = primesService.getPrimesUsingTrialDivision(maximumPossiblePrimeNumber);
        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimesUsingFirstApproach(maximumPossiblePrimeNumber))
                        .withRel(FIRST_APPROACH_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPossiblePrimeNumber))
                        .withSelfRel(),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(maximumPossiblePrimeNumber))
                        .withRel(SIEVE_OF_ERATOSTHENES_LABEL));
    }

    @GetMapping("/primesUsingSieveOfEratosthenes/{max}")
    public EntityModel<PrimeNumbers> getPrimesUsingSieveOfEratosthenes(
            @PathVariable("max") final int maximumPossiblePrimeNumber)
    {
        log.info("getPrimesUsingSieveOfEratosthenes({})", maximumPossiblePrimeNumber);
        final PrimeNumbers primes = primesService.getPrimesUsingSieveOfEratosthenes(maximumPossiblePrimeNumber);
        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimesUsingFirstApproach(maximumPossiblePrimeNumber))
                        .withRel(FIRST_APPROACH_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPossiblePrimeNumber))
                        .withRel(TRIAL_DIVISION_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(maximumPossiblePrimeNumber))
                        .withSelfRel());
    }
}
