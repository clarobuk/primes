## Technical Test - RESTful service to calculate and return prime numbers.

### Availability
Github: https://github.com/clarobuk/primes  
Azure: https://primes-primes.azuremicroservices.io  

### End Points
/ - gives usages detailing the available end-points https://primes-primes.azuremicroservices.io  
/primes/10 - produces the exact output specified in the test specification https://primes-primes.azuremicroservices.io/primes/10  
/primesUsingTrialDivision/{max} - uses a HATEOAS approach, returning the required results and details of other end-points https://primes-primes.azuremicroservices.io/primesUsingTrialDivision/10  

### Assumptions and Decisions
* Because it will return all the prime numbers up to the maximum specified there is no point in using anything larger than an int/Integer as the time to generate and the size of the payload would be unworkable.
* I did the first implementation without any research just to get something working but it turned out it would have been a fast algorithm if I had not used a list.
* I only added caching to the first implementation that I created to show its usage as the others were quite fast by themselves.
* Creating sub-packages for the different components of the API wasn't really necessary for such a small application