package sejong.europlanner.vo.request.user;

import lombok.Data;

@Data
public class RequestLogin {
    private String username;

    private String password;
}
