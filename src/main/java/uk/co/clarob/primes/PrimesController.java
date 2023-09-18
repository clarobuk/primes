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

    @GetMapping("/primes/{max}")
    public EntityModel<PrimeNumbers> getPrimesUpTo(@PathVariable("max") final int maximumPrimeNumber)
    {
        final PrimeNumbers primes = primesService.getPrimesUpTo(maximumPrimeNumber);

        return EntityModel.of(
                primes,
                linkTo(methodOn(PrimesController.class).getPrimesUpTo(maximumPrimeNumber)).withSelfRel(),
                linkTo(methodOn(PrimesController.class).getPrimesUsingTrialDivision(maximumPrimeNumber)).withSelfRel());
    }

    @GetMapping("/primes/byTrialDivision/{max}")
    public EntityModel<PrimeNumbers> getPrimesUsingTrialDivision(@PathVariable("max") final int maximumPrimeNumber)
    {
        final PrimeNumbers primes = primesService.getPrimesUsingTrialDivision(maximumPrimeNumber);

        return EntityModel.of(primes);
    }
}
