package snorlaxious.me.snore.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.ACCEPTED)
public class InvalidPageNumberException extends IllegalArgumentException {
    private static final long serialVersionUID = -1990301478904977236L;

    public InvalidPageNumberException() {
    }

    public InvalidPageNumberException(String s) {
        super(s);
    }

    public InvalidPageNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPageNumberException(Throwable cause) {
        super(cause);
    }
}
