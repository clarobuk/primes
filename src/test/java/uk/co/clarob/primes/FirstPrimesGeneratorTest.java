package uk.co.clarob.primes;

class FirstPrimesGeneratorTest extends PrimesGeneratorTestBase
{
    @Override
    PrimesGenerator createPrimesGenerator()
    {
        return new FistPrimesGenerator();
    }

    @Override
    String getImplementationClassName()
    {
        return this.getClass().getSimpleName();
    }
}