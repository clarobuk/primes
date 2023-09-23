package uk.co.clarob.primes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handle OutOfRangeException exceptions to return an appropriate HTTP Status Code. Not sure which code to use here as
 * this is due to the input value not being able to be processed by the generator being used. I used the web page:
 * <a href="https://www.restapitutorial.com/httpstatuscodes.html">...</a> and found the description of 403 looked the
 * best, see the description below.
 * <p>
 * The server understood the request, but is refusing to fulfill it. Authorization will not help and the request SHOULD
 * NOT be repeated. If the request method was not HEAD and the server wishes to make public why the request has not been
 * fulfilled, it SHOULD describe the reason for the refusal in the entity. If the server does not wish to make this
 * information available to the client, the status code 404 (Not Found) can be used instead.
 */
@ControllerAdvice
public class OutOfRangeAdvice
{
    @ResponseBody
    @ExceptionHandler(OutOfRangeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // Not sure what 400 status this should fall under
    public String outOfRangeHandler(final OutOfRangeException outOfRangeException)
    {
        return outOfRangeException.getMessage();
    }
}
