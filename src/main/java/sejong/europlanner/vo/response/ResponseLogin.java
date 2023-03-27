package sejong.europlanner.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseLogin {
    private Long id;

    private String username;

    private String jwtToken;
}
