package sejong.europlanner.vo.response.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDeleteBoard {
    private String message;
}
