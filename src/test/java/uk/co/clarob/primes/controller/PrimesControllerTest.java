package uk.co.clarob.primes.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import uk.co.clarob.primes.entity.PrimeNumbers;
import uk.co.clarob.primes.entity.Usage;
import uk.co.clarob.primes.service.PrimesService;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PrimesControllerTest
{
    private PrimesController primesController;
    private PrimesService mockPrimesService;

    @BeforeEach
    void setUp()
    {
        mockPrimesService = mock(PrimesService.class);
        primesController = new PrimesController(mockPrimesService);
    }

    @Test
    void shouldReturnInformation()
    {
        // arrange
        final Usage expectedValue = new Usage("Service to generate and return prime numbers up to the specified maximum value");
        // act
        final EntityModel<Usage> actualValue = primesController.information();
        // assert
        assertThat(actualValue.getContent(), is(equalTo(expectedValue)));
    }

    @Test
    void shouldReturnSimpleResult()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        when(mockPrimesService.getPrimesUsingFirstApproach(10)).thenReturn(new PrimeNumbers(10, asList(2, 3, 5, 7)));
        // act
        final PrimeNumbers actualValue = primesController.getPrimes(10);
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @Test
    void shouldReturnFirstApproachResult()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        when(mockPrimesService.getPrimesUsingFirstApproach(10)).thenReturn(new PrimeNumbers(10, asList(2, 3, 5, 7)));
        // act
        final EntityModel<PrimeNumbers> actualValue = primesController.getPrimesUsingFirstApproach(10);
        // assert
        assertThat(actualValue.getContent(), is(equalTo(expectedValue)));
    }

    @Test
    void shouldReturnTrialDivisionResult()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        when(mockPrimesService.getPrimesUsingTrialDivision(10)).thenReturn(new PrimeNumbers(10, asList(2, 3, 5, 7)));
        // act
        final EntityModel<PrimeNumbers> actualValue = primesController.getPrimesUsingTrialDivision(10);
        // assert
        assertThat(actualValue.getContent(), is(equalTo(expectedValue)));
    }

    @Test
    void shouldReturnSieveOfEratosthenesResult()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        when(mockPrimesService.getPrimesUsingSieveOfEratosthenes(10)).thenReturn(new PrimeNumbers(10, asList(2, 3, 5, 7)));
        // act
        final EntityModel<PrimeNumbers> actualValue = primesController.getPrimesUsingSieveOfEratosthenes(10);
        // assert
        assertThat(actualValue.getContent(), is(equalTo(expectedValue)));
    }
}