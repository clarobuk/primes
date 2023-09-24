package uk.co.clarob.primes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PrimesApplication
{
    public static void main(final String... arguments)
    {
        SpringApplication.run(PrimesApplication.class, arguments);
    }
}
