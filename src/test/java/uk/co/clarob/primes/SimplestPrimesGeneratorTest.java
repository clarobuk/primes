package uk.co.clarob.primes;

class SimplestPrimesGeneratorTest extends PrimesGeneratorTestBase
{
    @Override
    PrimesGenerator createPrimesGenerator()
    {
        return new SimplestPrimesGenerator();
    }
}