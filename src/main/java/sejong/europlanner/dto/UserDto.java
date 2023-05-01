package sejong.europlanner.dto;

import lombok.Data;
import sejong.europlanner.enumtype.Gender;

@Data
public class UserDto {
    private Long id;

    private String username;

    private String password;

    private String password1;

    private String name;

    private String birthdate;

    private Gender gender;
}
