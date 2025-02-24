package edu.school21.shopapi.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
