package uk.co.clarob.primes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public PrimeNumbers getPrimesUpTo(@PathVariable("max") final int maximumPrimeNumber)
    {
        return primesService.getPrimesUpTo(maximumPrimeNumber);
    }
}
