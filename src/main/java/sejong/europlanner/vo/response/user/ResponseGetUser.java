package sejong.europlanner.vo.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import sejong.europlanner.enumtype.Gender;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGetUser {
    private String username;
    private String name;
    private String birthdate;
    private Gender gender;
}
