package sejong.europlanner.service.serviceinterface;

import sejong.europlanner.dto.UserDto;
import sejong.europlanner.vo.response.user.ResponseLogin;
import sejong.europlanner.vo.response.user.ResponseUser;

public interface UserService {
    ResponseUser checkValidation(UserDto userDto);

    ResponseLogin loginValidation(UserDto userDto);
}
