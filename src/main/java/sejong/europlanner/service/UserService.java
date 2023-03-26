package sejong.europlanner.service;

import sejong.europlanner.dto.UserDto;
import sejong.europlanner.vo.response.ResponseUser;

public interface UserService {
    ResponseUser checkValidation(UserDto userDto);
}
