package uk.co.clarob.primes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PrimesControllerIT
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetPrimesUpTo10() throws Exception
    {
        // arrange
        final String expectedValue = "{\"initial\":10,\"primes\":[2,3,5,7]}";
        // act
        final String actualValue = mockMvc
                .perform(get("/primes/10")
                                 .accept("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @Test
    void shouldGetPrimesUpTo10UsingFirstApproach() throws Exception
    {
        // arrange
        final String expectedValue = "\"initial\":10,\"primes\":[2,3,5,7]";
        // act
        final String actualValue = mockMvc
                .perform(get("/primesUsingFirstApproach/10")
                                 .accept("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(containsString(expectedValue)));
    }

    @Test
    void shouldGetPrimesUpTo10UsingTrialDivision() throws Exception
    {
        // arrange
        final String expectedValue = "\"initial\":10,\"primes\":[2,3,5,7]";
        // act
        final String actualValue = mockMvc
                .perform(get("/primesUsingTrialDivision/10")
                                 .accept("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(containsString(expectedValue)));
    }

    @Test
    void shouldGetPrimesUpTo10UsingSieveOfEratosthenes() throws Exception
    {
        // arrange
        final String expectedValue = "\"initial\":10,\"primes\":[2,3,5,7]";
        // act
        final String actualValue = mockMvc
                .perform(get("/primesUsingSieveOfEratosthenes/10")
                                 .accept("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(containsString(expectedValue)));
    }

    @Test
    void shouldFailToGetPrimesUpTo1() throws Exception
    {
        // arrange
        final String expectedValue = "Maximum possible prime number (1) provided is less than 2 which is not valid for generating prime numbers.";
        // act
        final String actualValue = mockMvc
                .perform(get("/primes/1")
                                 .accept("application/json"))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @Test
    void shouldFailToGetPrimesUpToMinus10() throws Exception
    {
        // arrange
        final String expectedValue = "Maximum possible prime number (-10) provided is less than 2 which is not valid for generating prime numbers.";
        // act
        final String actualValue = mockMvc
                .perform(get("/primes/-10")
                                 .accept("application/json"))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }

    @Test
    void shouldFailToGetPrimesUpTo10000000() throws Exception
    {
        // arrange
        final String expectedValue = "Maximum possible prime number (10000000) provided is bigger than the practical maximum for the algorithm used by this prime number generator, there may be another algorithm on the API that will support this request.";
        // act
        final String actualValue = mockMvc
                .perform(get("/primes/10000000")
                                 .accept("application/json"))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }
}