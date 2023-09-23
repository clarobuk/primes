package uk.co.clarob.primes;

class FistApproachPrimesGeneratorTest extends PrimesGeneratorTestBase
{
    @Override
    PrimesGenerator createPrimesGenerator()
    {
        return new FistApproachPrimesGenerator();
    }

    @Override
    String getImplementationClassName()
    {
        return this.getClass().getSimpleName();
    }
}