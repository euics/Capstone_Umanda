package sejong.europlanner.service.serviceinterface;

import org.springframework.http.HttpStatus;
import sejong.europlanner.vo.response.ResponseKakaoLogin;

public interface KakaoService {
    ResponseKakaoLogin getKakaoAccessToken (String code);

    HttpStatus kakaoLogout(String accessToken);
}
