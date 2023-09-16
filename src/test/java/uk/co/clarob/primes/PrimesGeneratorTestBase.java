package uk.co.clarob.primes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Abstract class with all the unit tests for prime number generators, so that all the implementations can be checked
 * using the same tests.
 */
abstract class PrimesGeneratorTestBase
{
    private static final Logger log = LoggerFactory.getLogger(PrimesGeneratorTestBase.class);

    private PrimesGenerator primesGenerator;

    abstract PrimesGenerator createPrimesGenerator();

    @BeforeEach
    void setUp()
    {
        primesGenerator = createPrimesGenerator();
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
        final StopWatch stopWatch = new StopWatch();
        // act
        stopWatch.start();
        final List<Integer> actualValue = primesGenerator.generate(7920);
        stopWatch.stop();
        log.info("Finding first 1,000 primes took {} millis", stopWatch.getLastTaskTimeMillis());
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
        final StopWatch stopWatch = new StopWatch();
        // act
        stopWatch.start();
        final List<Integer> actualValue = primesGenerator.generate(104730);
        stopWatch.stop();
        log.info("Finding first 10,000 primes took {} millis", stopWatch.getLastTaskTimeMillis());
        // assert
        assertThat(actualValue.get(9), is(equalTo(29))); // 10th
        assertThat(actualValue.get(99), is(equalTo(541))); // 100th
        assertThat(actualValue.get(999), is(equalTo(7919))); // 1000th
        assertThat(actualValue.get(9999), is(equalTo(104729))); // 10000th
    }
}