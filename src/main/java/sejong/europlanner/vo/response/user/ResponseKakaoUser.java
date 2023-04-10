package sejong.europlanner.vo.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseKakaoUser {
    private String name;

    private String profile;

    private String jwtToken;
}
