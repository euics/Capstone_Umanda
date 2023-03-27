package sejong.europlanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.service.serviceImpl.KakaoServiceImpl;

@RestController
@RequestMapping("/kakao")
public class KakaoController {
    private final KakaoServiceImpl kakaoService;

    @Autowired
    public KakaoController(KakaoServiceImpl kakaoService) {
        this.kakaoService = kakaoService;
    }

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
    @GetMapping("/login")
    public void kakaoCallback(@RequestParam String code) {
        String accessToken = kakaoService.getKakaoAccessToken(code);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String accessToken) {
        try {
            HttpStatus status = kakaoService.kakaoLogout(accessToken);
            return new ResponseEntity<>("Logout successful", status);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
