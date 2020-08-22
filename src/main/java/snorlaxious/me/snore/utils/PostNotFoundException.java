package snorlaxious.me.snore.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT)
public class PostNotFoundException extends IllegalArgumentException {
    public PostNotFoundException() {
    }

    public PostNotFoundException(String s) {
        super(s);
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostNotFoundException(Throwable cause) {
        super(cause);
    }
}
