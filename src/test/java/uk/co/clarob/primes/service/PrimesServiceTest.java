package uk.co.clarob.primes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.co.clarob.primes.controller.OutOfRangeException;
import uk.co.clarob.primes.entity.PrimeNumbers;
import uk.co.clarob.primes.generator.PrimesGenerator;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * NOTE: I was only able to get to 95% line coverage with this test due to the try-finally in the method
 * validateAndCallGenerator(), even though these tests exit the finally both successfully and with exceptions.
 */
class PrimesServiceTest
{
    private PrimesService primesService;
    private PrimesGenerator mockFirstApproachPrimesGenerator;
    private PrimesGenerator mockTrialDivisionPrimesGenerator;
    private PrimesGenerator mockSieveOfEratosthenesPrimesGenerator;

    @BeforeEach
    void setUp()
    {
        mockFirstApproachPrimesGenerator = mock(PrimesGenerator.class);
        mockTrialDivisionPrimesGenerator = mock(PrimesGenerator.class);
        mockSieveOfEratosthenesPrimesGenerator = mock(PrimesGenerator.class);
        primesService = new PrimesService(
                mockFirstApproachPrimesGenerator,
                mockTrialDivisionPrimesGenerator,
                mockSieveOfEratosthenesPrimesGenerator);
    }

    @DisplayName("should generate using first approach")
    @Test
    void shouldGenerateUsingFirstApproach()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        when(mockFirstApproachPrimesGenerator.getPracticalMaximum()).thenReturn(1000);
        when(mockFirstApproachPrimesGenerator.generate(10)).thenReturn(asList(2, 3, 5, 7));
        // act
        final PrimeNumbers actualValue = primesService.getPrimesUsingFirstApproach(10);
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @DisplayName("should generate using Trial Division")
    @Test
    void shouldGenerateUsingTrialDivision()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        when(mockTrialDivisionPrimesGenerator.getPracticalMaximum()).thenReturn(1000);
        when(mockTrialDivisionPrimesGenerator.generate(10)).thenReturn(asList(2, 3, 5, 7));
        // act
        final PrimeNumbers actualValue = primesService.getPrimesUsingTrialDivision(10);
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @DisplayName("should generate using Sieve Of Eratosthenes")
    @Test
    void shouldGenerateUsingSieveOfEratosthenes()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        when(mockSieveOfEratosthenesPrimesGenerator.getPracticalMaximum()).thenReturn(1000);
        when(mockSieveOfEratosthenesPrimesGenerator.generate(10)).thenReturn(asList(2, 3, 5, 7));
        // act
        final PrimeNumbers actualValue = primesService.getPrimesUsingSieveOfEratosthenes(10);
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @DisplayName("should raise an exception when maximum is 1")
    @Test
    void shouldRaiseExceptionWhenMaximumIsTooSmall()
    {
        // arrange
        final String expectedValue = "Maximum possible prime number (1) provided is less than 2 which is not valid for generating prime numbers.";
        // act
        // assert
        final OutOfRangeException outOfRangeException = assertThrows(
                OutOfRangeException.class,
                () -> primesService.getPrimesUsingFirstApproach(1));
        assertThat(outOfRangeException.getMessage(), is(equalTo(expectedValue)));
    }

    @DisplayName("should raise an exception when maximum is negative")
    @Test
    void shouldRaiseExceptionWhenMaximumIsNegative()
    {
        // arrange
        final String expectedValue = "Maximum possible prime number (-10) provided is less than 2 which is not valid for generating prime numbers.";
        // act
        // assert
        final OutOfRangeException outOfRangeException = assertThrows(
                OutOfRangeException.class,
                () -> primesService.getPrimesUsingFirstApproach(-10));
        assertThat(outOfRangeException.getMessage(), is(equalTo(expectedValue)));
    }

    @DisplayName("should raise an exception when maximum is greater than the practical maximum")
    @Test
    void shouldRaiseExceptionWhenMaximumIsGreaterThanPracticalMaximum()
    {
        // arrange
        final String expectedValue = "Maximum possible prime number (1001) provided is bigger than the practical maximum for the algorithm used by this prime number generator, there may be another algorithm on the API that will support this request.";
        when(mockFirstApproachPrimesGenerator.getPracticalMaximum()).thenReturn(1000);
        // act
        // assert
        final OutOfRangeException outOfRangeException = assertThrows(
                OutOfRangeException.class,
                () -> primesService.getPrimesUsingFirstApproach(1001));
        assertThat(outOfRangeException.getMessage(), is(equalTo(expectedValue)));
    }
}