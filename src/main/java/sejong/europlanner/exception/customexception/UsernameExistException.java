package sejong.europlanner.exception.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameExistException extends RuntimeException{
    public UsernameExistException(String m) {
        super(m);
    }
}
