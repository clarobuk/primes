package uk.co.clarob.primes;

class ArraySievePrimesGeneratorTest extends PrimesGeneratorTestBase
{
    @Override
    PrimesGenerator createPrimesGenerator()
    {
        return new ArraySievePrimesGenerator();
    }

    @Override
    String getImplementationClassName()
    {
        return this.getClass().getSimpleName();
    }
}