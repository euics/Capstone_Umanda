package sejong.europlanner.vo.request.user;

import lombok.Data;
import sejong.europlanner.enumtype.Gender;

@Data
public class RequestUser {
    private String username;

    private String password;

    private String password1;

    private String name;

    private Integer birthdate;

    private Gender gender;
}
