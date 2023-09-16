package uk.co.clarob.primes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
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
                                 .contentType("application/json")
                                 .accept("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        // assert
        assertThat(actualValue, is(equalTo(expectedValue)));
    }
}