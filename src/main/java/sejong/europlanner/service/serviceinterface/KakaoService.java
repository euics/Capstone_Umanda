package sejong.europlanner.service.serviceinterface;

import org.springframework.http.HttpStatus;

public interface KakaoService {
    String getKakaoAccessToken (String code);

    HttpStatus kakaoLogout(String accessToken);
}
