package sejong.europlanner.service.serviceinterface;

import org.springframework.http.HttpStatus;
import sejong.europlanner.vo.response.ResponseKakaoLogin;

import java.util.HashMap;

public interface KakaoService {
    ResponseKakaoLogin getKakaoAccessToken (String code);

    void kakaoLogout(String accessToken);

    HashMap<String, Object> getUserInfo (String access_Token);
}
