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

    private Integer birthdate;

    private Gender gender;
}
