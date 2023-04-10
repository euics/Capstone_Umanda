package sejong.europlanner.vo.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseNaverUser {
    private String name;

    private String gender;

    private String birthyear;

    private String profile;

    private String jwtToken;
}
