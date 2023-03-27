package sejong.europlanner.service.serviceinterface;

import sejong.europlanner.dto.UserDto;
import sejong.europlanner.vo.response.ResponseLogin;
import sejong.europlanner.vo.response.ResponseUser;

public interface UserService {
    ResponseUser checkValidation(UserDto userDto);

    ResponseLogin loginValidation(UserDto userDto);
}
