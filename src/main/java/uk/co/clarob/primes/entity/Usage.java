package uk.co.clarob.primes.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * Used to return information about the API from default end-point.
 */
public class Usage
{
    private final String usage;

    public Usage(final String usage)
    {
        this.usage = usage;
    }

    public String getUsage()
    {
        return usage;
    }

    @Override
    public boolean equals(final Object thatObject)
    {
        if (this == thatObject)
        {
            return true;
        }

        if (!(thatObject instanceof final Usage that))
        {
            return false;
        }

        return new EqualsBuilder()
                .append(usage, that.usage)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37)
                .append(usage)
                .toHashCode();
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this, JSON_STYLE)
                .append("usage", usage)
                .toString();
    }
}
