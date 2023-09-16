package uk.co.clarob.primes;

class FirstPrimesGeneratorTest extends PrimesGeneratorTestBase
{
    @Override
    PrimesGenerator createPrimesGenerator()
    {
        return new FistPrimesGenerator();
    }
}