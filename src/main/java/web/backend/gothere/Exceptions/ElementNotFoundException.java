package web.backend.gothere.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No elements found")
public class  ElementNotFoundException extends RuntimeException{

}