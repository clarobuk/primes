package uk.co.clarob.primes.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * An entity used to hold the results of the calculations formatted according to the test specification. Standard
 * equals(), hashCode() and toString() methods added principally to support testing.
 */
// ClassCanBeRecord - cosmetic preference for seeing code in full
@SuppressWarnings("ClassCanBeRecord")
public class PrimeNumbers
{
    final int initial;
    final List<Integer> primes;

    public PrimeNumbers(final int initial, final List<Integer> primes)
    {
        this.initial = initial;
        this.primes = primes;
    }

    public int getInitial()
    {
        return initial;
    }

    public List<Integer> getPrimes()
    {
        return primes;
    }

    @Override
    public boolean equals(final Object thatObject)
    {
        if (this == thatObject)
        {
            return true;
        }
        if (!(thatObject instanceof final PrimeNumbers primeNumbers))
        {
            return false;
        }

        return new EqualsBuilder()
                .append(initial, primeNumbers.initial)
                .append(this.primes, primeNumbers.primes)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(initial)
                .append(primes)
                .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("initial", initial)
                .append("primes", primes)
                .toString();
    }
}
