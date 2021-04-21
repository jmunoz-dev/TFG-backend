package web.backend.gothere.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOOP_DETECTED, reason = "Can not create code")
public class OfferCodeException extends RuntimeException{
    
}
