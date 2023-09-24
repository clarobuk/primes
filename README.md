## Technical Test - RESTful service to calculate and return prime numbers.

### Availability
Github: https://github.com/clarobuk/primes  
Azure: https://primes-primes.azuremicroservices.io  

### End Points
/ - gives usages detailing the available end-points https://primes-primes.azuremicroservices.io  
/primes/10 - produces the exact output specified in the test specification https://primes-primes.azuremicroservices.io/primes/10  
/primesUsingTrialDivision/{max} - uses a HATEOAS approach, returning the required results and details of other end-points https://primes-primes.azuremicroservices.io/primesUsingTrialDivision/10  
/primesUsingSieveOfEratosthenes/{max}  
/primesUsingFirstApproach/{max}  

### Assumptions and Decisions
* Because it will return all the prime numbers up to the maximum specified there is no point in using anything larger than an int/Integer as the time to generate and the size of the payload would be unworkable.
* I did the first implementation without any research just to get something working but it turned out it would have been a fast algorithm if I had not used a list.
* I only added caching to the first implementation that I created to show its usage as the others were quite fast by themselves.
* Creating sub-packages for the different components of the API wasn't really necessary for such a small application

### Requirements and Extensions met
* Java 17
* Maven
* 95% code coverage with unit tests
* Integration test for controller
* Spring Boot used, started with Spring initializr
* Also used Spring Web, Cache, Hateoas, DevTools
* Available in GitHub
* Deployed on Azure
* Can respond with JSON or XML
* Caching added to one of the implementations
* 3 different implementations

### Azure Integration
* mvn com.microsoft.azure:azure-spring-apps-maven-plugin:1.19.0:config -DadvancedOptions 
* mvn azure-spring-apps:deploy
