package sejong.europlanner.exception.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadRequestException extends RuntimeException{
    public BadRequestException(String m) {
        super(m);
    }
}
