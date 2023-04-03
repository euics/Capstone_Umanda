package sejong.europlanner.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.service.serviceImpl.KakaoServiceImpl;
import sejong.europlanner.service.serviceinterface.KakaoService;

import java.io.IOException;

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
    public String kakaoCallback(@RequestParam String code) {

        return kakaoService.getAccessToken(code);
    }

    @PostMapping("/user")
    public String kakaoGetUser(@RequestHeader("Authorization") String accessToken) throws IOException {
        JsonNode userProfile = kakaoService.getUserProfile(accessToken);

        return userProfile.toString();
    }

    @PostMapping("/logout")
    public HttpStatus kakaoLogout(@RequestHeader("Authorization") String accessToken) {
        ResponseEntity<String> response = kakaoService.logout(accessToken);

        return response.getStatusCode();
    }
}
