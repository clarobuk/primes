package uk.co.clarob.primes.entity;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Not necessary, but helped with code coverage
 */
class UsageTest
{
    @Test
    void shouldUseAllMethodsImplemented()
    {
        // arrange
        final Usage expectedValue = new Usage("Details");
        final Usage differentValue = new Usage("Different");
        // act
        final Usage actualValue = new Usage("Details");
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
        assertThat(actualValue, is(equalTo(actualValue)));
        assertThat(actualValue, is(not(equalTo(differentValue))));
        assertThat(actualValue, is(not(equalTo("Details"))));
        assertThat(actualValue.hashCode(), is(equalTo(expectedValue.hashCode())));
        assertThat(actualValue.getUsage(), is(equalTo("Details")));
        assertThat(actualValue.toString(), is(equalTo("{\"usage\":\"Details\"}")));
    }
}