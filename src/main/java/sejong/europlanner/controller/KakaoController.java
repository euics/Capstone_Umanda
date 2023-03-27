package sejong.europlanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.service.serviceImpl.KakaoService;

@RestController
@RequestMapping("/kakao")
public class KakaoController {
    private final KakaoService oAuthService;

    @Autowired
    public KakaoController(KakaoService oAuthService) {
        this.oAuthService = oAuthService;
    }

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @GetMapping("/login")
    public void kakaoCallback(@RequestParam String code) {
        String accessToken = oAuthService.getKakaoAccessToken(code);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String accessToken) {
        try {
            HttpStatus status = oAuthService.kakaoLogout(accessToken);
            return new ResponseEntity<>("Logout successful", status);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
