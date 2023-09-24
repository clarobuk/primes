package uk.co.clarob.primes.entity;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Not necessary, but helped with code coverage
 */
class PrimeNumbersTest
{
    @Test
    void shouldUseAllMethodsImplemented()
    {
        // arrange
        final PrimeNumbers expectedValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        final PrimeNumbers differentValue = new PrimeNumbers(10, asList(2, 3, 5, 7, 11));
        // act
        final PrimeNumbers actualValue = new PrimeNumbers(10, asList(2, 3, 5, 7));
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
        assertThat(actualValue, is(equalTo(actualValue)));
        assertThat(actualValue, is(not(equalTo(differentValue))));
        assertThat(actualValue, is(not(equalTo("Details"))));
        assertThat(actualValue.hashCode(), is(equalTo(expectedValue.hashCode())));
        assertThat(actualValue.getInitial(), is(equalTo(10)));
        assertThat(actualValue.getPrimes(), is(equalTo(asList(2, 3, 5, 7))));
        assertThat(actualValue.toString(), is(equalTo("{\"initial\":10,\"primes\":[2,3,5,7]}")));
    }
}