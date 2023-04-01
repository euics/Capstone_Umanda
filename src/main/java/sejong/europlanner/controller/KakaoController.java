package sejong.europlanner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.service.serviceImpl.KakaoServiceImpl;
import sejong.europlanner.service.serviceinterface.KakaoService;
import sejong.europlanner.vo.response.ResponseKakaoLogin;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/kakao")
@Slf4j
public class KakaoController {
    private final KakaoService kakaoService;

    @Autowired
    public KakaoController(KakaoServiceImpl kakaoService) {
        this.kakaoService = kakaoService;
    }

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @GetMapping("/login")
    public ResponseEntity<ResponseKakaoLogin> kakaoCallback(@RequestParam String code,
                                                            HttpSession httpSession) {
        ResponseKakaoLogin responseKakaoLogin = kakaoService.getKakaoAccessToken(code);

        return ResponseEntity.ok().body(responseKakaoLogin);
    }

    @PostMapping("/user")
    public ResponseEntity<HashMap<String, Object >> kakaoUser(@RequestHeader("Authorization") String accessToken){
        HashMap<String, Object> userInfo = kakaoService.getUserInfo(accessToken);

        return ResponseEntity.ok().body(userInfo);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String accessToken) {
        try {
            HttpStatus status = kakaoService.kakaoLogout(accessToken);
            return new ResponseEntity<>("로그아웃 성공", status);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
