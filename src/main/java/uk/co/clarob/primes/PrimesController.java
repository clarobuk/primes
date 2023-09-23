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
        return EntityModel.of(
                new Usage("Service to generate and return prime numbers up to the specified maximum value"),
                linkTo(methodOn(PrimesController.class).getPrimes(10))
                        .withRel("Primes using default approach"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingFirstApproach(10))
                        .withRel("Primes using first approach"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(10))
                        .withRel("Primes using Trial Division"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(10))
                        .withRel("Primes using Sieve Of Eratosthenes"));

    }

    @GetMapping("/primes/{max}")
    public PrimeNumbers getPrimes(@PathVariable("max") final int maximumPrimeNumber)
    {
        return primesService.getPrimes(maximumPrimeNumber);
    }

    @GetMapping("/primesUsingFirstApproach/{max}")
    public EntityModel<PrimeNumbers> getPrimesUsingFirstApproach(@PathVariable("max") final int maximumPrimeNumber)
    {
        final PrimeNumbers primes = primesService.getPrimes(maximumPrimeNumber);

        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimes(maximumPrimeNumber))
                        .withSelfRel(),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPrimeNumber))
                        .withRel("Primes using Trial Division"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(maximumPrimeNumber))
                        .withRel("Primes using Sieve Of Eratosthenes"));
    }

    @GetMapping("/primesUsingTrialDivision/{max}")
    public EntityModel<PrimeNumbers> getPrimesUsingTrialDivision(@PathVariable("max") final int maximumPrimeNumber)
    {
        final PrimeNumbers primes = primesService.getPrimesUsingTrialDivision(maximumPrimeNumber);

        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimes(maximumPrimeNumber))
                        .withRel("Primes using first approach"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPrimeNumber))
                        .withSelfRel(),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(maximumPrimeNumber))
                        .withRel("Primes using Sieve Of Eratosthenes"));
    }

    @GetMapping("/primesUsingSieveOfEratosthenes/{max}")
    public EntityModel<PrimeNumbers> getPrimesUsingSieveOfEratosthenes(@PathVariable("max") final int maximumPrimeNumber)
    {
        final PrimeNumbers primes = primesService.getPrimesUsingSieveOfEratosthenes(maximumPrimeNumber);

        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimes(maximumPrimeNumber))
                        .withRel("Primes using first approach"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPrimeNumber))
                        .withRel("Primes using Trial Division"),
                linkTo(methodOn(PrimesController.class).getPrimesUsingSieveOfEratosthenes(maximumPrimeNumber))
                        .withSelfRel());
    }
}
