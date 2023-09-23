package uk.co.clarob.primes.generator;

class SieveOfEratosthenesPrimesGeneratorTest extends PrimesGeneratorTestBase
{
    @Override
    PrimesGenerator createPrimesGenerator()
    {
        return new SieveOfEratosthenesPrimesGenerator();
    }

    @Override
    String getImplementationClassName()
    {
        return this.getClass().getSimpleName();
    }
}