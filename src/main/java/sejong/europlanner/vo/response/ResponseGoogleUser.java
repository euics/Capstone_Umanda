package sejong.europlanner.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGoogleUser {
    private String name;

    private String profile;

    private String jwtToken;
}
