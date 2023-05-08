package sejong.europlanner.vo.response.comments;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDeleteComments {
    private String message;
}
