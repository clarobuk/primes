package uk.co.clarob.primes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uk.co.clarob.primes.entity.PrimeNumbers;
import uk.co.clarob.primes.entity.Usage;
import uk.co.clarob.primes.service.PrimesService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Controller offering several end-points to calculate and return all the prime numbers up to a maximum. There is also
 * an information end-point returning links to all the others.
 */
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

    /**
     * Return some information about what's available on the API.
     *
     * @return Some text and links to the available end-points.
     */
    @GetMapping(path="/", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Usage> information()
    {
        log.info("information()");
        return EntityModel.of(
                new Usage("Service to generate and return prime numbers up to the specified maximum value"),
                WebMvcLinkBuilder.linkTo(methodOn(PrimesController.class).getPrimes(10))
                                 .withRel("Primes using default approach"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingFirstApproach(10))
                        .withRel(FIRST_APPROACH_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(10))
                        .withRel(TRIAL_DIVISION_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(10))
                        .withRel(SIEVE_OF_ERATOSTHENES_LABEL));

    }

    /**
     * The required end-point to return all the prime numbers up to and possibly including the maximum specified.
     *
     * @param maximumPossiblePrimeNumber The limit to go up to when generating prime numbers.
     * @return An object containing the limit and the list of prime numbers found up to that limit.
     */
    @GetMapping(path="/primes/{max}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PrimeNumbers getPrimes(@PathVariable("max") final int maximumPossiblePrimeNumber)
    {
        log.info("getPrimes({})", maximumPossiblePrimeNumber);
        return primesService.getPrimesUsingFirstApproach(maximumPossiblePrimeNumber);
    }

    /**
     * Get all the prime numbers up to the maximum using the first approach specifically. This uses a HATEOAS approach
     * and returns details of this end-point and the other ones for the specific implementations.
     *
     * @param maximumPossiblePrimeNumber The limit to go up to when generating prime numbers.
     * @return An object containing the limit and the list of prime numbers found up to that limit, wrapped with links
     * to all the end-points offering specific prime number generators.
     */
    @GetMapping(path="/primesUsingFirstApproach/{max}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityModel<PrimeNumbers> getPrimesUsingFirstApproach(
            @PathVariable("max") final int maximumPossiblePrimeNumber)
    {
        log.info("getPrimesUsingFirstApproach({})", maximumPossiblePrimeNumber);
        final PrimeNumbers primes = primesService.getPrimesUsingFirstApproach(maximumPossiblePrimeNumber);
        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimesUsingFirstApproach(maximumPossiblePrimeNumber))
                        .withSelfRel(),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPossiblePrimeNumber))
                        .withRel(TRIAL_DIVISION_LABEL),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(maximumPossiblePrimeNumber))
                        .withRel(SIEVE_OF_ERATOSTHENES_LABEL));
    }

    /**
     * Get all the prime numbers up to the maximum using the Trial Division algorithm specifically. This uses a HATEOAS
     * approach
     * and returns details of this end-point and the other ones for the specific implementations.
     *
     * @param maximumPossiblePrimeNumber The limit to go up to when generating prime numbers.
     * @return An object containing the limit and the list of prime numbers found up to that limit, wrapped with links
     * to all the end-points offering specific prime number generators.
     */
    @GetMapping(path="/primesUsingTrialDivision/{max}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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

    /**
     * Get all the prime numbers up to the maximum using the Sieve Of Eratosthenes algorithm specifically. This uses a
     * HATEOAS approach and returns details of this end-point and the other ones for the specific implementations.
     *
     * @param maximumPossiblePrimeNumber The limit to go up to when generating prime numbers.
     * @return An object containing the limit and the list of prime numbers found up to that limit, wrapped with links
     * to all the end-points offering specific prime number generators.
     */
    @GetMapping(path="/primesUsingSieveOfEratosthenes/{max}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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
