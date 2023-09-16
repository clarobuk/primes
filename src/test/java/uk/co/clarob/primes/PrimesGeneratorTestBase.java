package uk.co.clarob.primes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        final List<Integer> expectedValue = asList(
                2,
                3,
                5,
                7,
                11,
                13,
                17,
                19,
                23,
                29,
                31,
                37,
                41,
                43,
                47,
                53,
                59,
                61,
                67,
                71,
                73,
                79,
                83,
                89,
                97);
        // act
        final List<Integer> actualValue = primesGenerator.generate(100);
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }
}