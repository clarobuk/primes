package uk.co.clarob.primes.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import uk.co.clarob.primes.controller.OutOfRangeException;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Abstract class with all the unit tests for prime number generators, so that all the implementations can be checked
 * using the same tests. The sample prime numbers used in the series of tests that check large maximum vales was
 * obtained from <a href="https://www.bigprimes.net">Bigprimes</a>
 */
abstract class PrimesGeneratorTestBase
{
    private static final Logger log = LoggerFactory.getLogger(PrimesGeneratorTestBase.class);

    private static final NumberFormat numberFormat = new DecimalFormat("#,###");

    private PrimesGenerator primesGenerator;
    private String implementationClassName;
    private StopWatch stopWatch;

    abstract PrimesGenerator createPrimesGenerator();
    abstract String getImplementationClassName();

    @BeforeEach
    void setUp()
    {
        primesGenerator = createPrimesGenerator();
        implementationClassName = getImplementationClassName();
        stopWatch = new StopWatch();
    }

    @DisplayName("should generate all primes up to 10")
    @Test
    void shouldFindPrimesUpToTen()
    {
        // arrange
        final List<Integer> expectedValue = asList(2, 3, 5, 7);
        // act
        final List<Integer> actualValue = primesGenerator.generate(10);
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @DisplayName("should generate all primes up to 100")
    @Test
    void shouldFindPrimesUpTo100()
    {
        // arrange
        final List<Integer> expectedValue = asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        // act
        final List<Integer> actualValue = primesGenerator.generate(100);
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @DisplayName("should generate first 1,000 primes")
    @Test
    void shouldGenerateFirst1000Primes()
    {
        // arrange
        // act
        final List<Integer> actualValue = generateAndLogTimings(7920, 1000);
        // assert
        assertThat(actualValue.get(9), is(equalTo(29))); // 10th
        assertThat(actualValue.get(99), is(equalTo(541))); // 100th
        assertThat(actualValue.get(999), is(equalTo(7919))); // 1000th
    }

    @DisplayName("should generate first 10,000 primes")
    @Test
    void shouldGenerateFirst10000Primes()
    {
        // arrange
        // act
        final List<Integer> actualValue = generateAndLogTimings(104730, 10000);
        // assert
        assertThat(actualValue.get(9), is(equalTo(29))); // 10th
        assertThat(actualValue.get(99), is(equalTo(541))); // 100th
        assertThat(actualValue.get(999), is(equalTo(7919))); // 1000th
        assertThat(actualValue.get(9999), is(equalTo(104729))); // 10000th
    }

    @DisplayName("should generate first 100,000 primes")
    @Test
    void shouldGenerateFirst100000Primes()
    {
        // arrange
        // act
        if (primesGenerator.getPracticalMaximum() < 1000000)
        {
            return;
        }
        final List<Integer> actualValue = generateAndLogTimings(1299709, 100000);
        // assert
        assertThat(actualValue.get(9), is(equalTo(29))); // 10th
        assertThat(actualValue.get(99), is(equalTo(541))); // 100th
        assertThat(actualValue.get(999), is(equalTo(7919))); // 1000th
        assertThat(actualValue.get(9999), is(equalTo(104729))); // 10000th
        assertThat(actualValue.get(99999), is(equalTo(1299709))); // 10000th
    }

    @DisplayName("should generate first 100,000 primes")
    @Test
    void shouldGenerateFirst1000000Primes()
    {
        // arrange
        // act
        if (primesGenerator.getPracticalMaximum() < 16000000)
        {
            return;
        }
        final List<Integer> actualValue = generateAndLogTimings(15485865, 1000000);
        // assert
        assertThat(actualValue.get(9), is(equalTo(29))); // 10th
        assertThat(actualValue.get(99), is(equalTo(541))); // 100th
        assertThat(actualValue.get(999), is(equalTo(7919))); // 1,000th
        assertThat(actualValue.get(9999), is(equalTo(104729))); // 10,000th
        assertThat(actualValue.get(99999), is(equalTo(1299709))); // 100,000th
        assertThat(actualValue.get(999999), is(equalTo(15485863))); // 1,000,000th
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
                () -> primesGenerator.generate(1));
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
                () -> primesGenerator.generate(-10));
        assertThat(outOfRangeException.getMessage(), is(equalTo(expectedValue)));
    }

    @DisplayName("should raise an exception when maximum is greater than the practical maximum")
    @Test
    void shouldRaiseExceptionWhenMaximumIsGreaterThanPracticalMaximum()
    {
        // arrange
        final int maximumThatIsTooLarge = primesGenerator.getPracticalMaximum() + 1;
        // act
        // assert
        assertThrows(
                OutOfRangeException.class,
                () -> primesGenerator.generate(maximumThatIsTooLarge));
    }


    private List<Integer> generateAndLogTimings(final int maximumPossibleNumber, final int expectedNumberOfPrimes)
    {
        stopWatch.start();
        final List<Integer> actualValue = primesGenerator.generate(maximumPossibleNumber);
        stopWatch.stop();
        final float elapsedTime = (float) stopWatch.getLastTaskTimeNanos() / 1000000;
        log.info("Finding first {} primes using {} took {} millis", numberFormat.format(expectedNumberOfPrimes), implementationClassName, elapsedTime);
        assertThat(actualValue.size(), is(equalTo(expectedNumberOfPrimes)));
        return actualValue;
    }
}