package uk.co.clarob.primes;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Run all unit tests in project to check code coverage.
 */
@Suite
@SelectPackages({
        "uk.co.clarob.primes.controller",
        "uk.co.clarob.primes.entity",
        "uk.co.clarob.primes.generator",
        "uk.co.clarob.primes.service"})
public class PrimeTestSuite
{
}
