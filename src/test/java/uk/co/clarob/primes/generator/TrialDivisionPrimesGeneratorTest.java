package uk.co.clarob.primes.generator;

class TrialDivisionPrimesGeneratorTest extends PrimesGeneratorTestBase
{
    @Override
    PrimesGenerator createPrimesGenerator()
    {
        return new TrialDivisionPrimesGenerator();
    }

    @Override
    String getImplementationClassName()
    {
        return this.getClass().getSimpleName();
    }

}