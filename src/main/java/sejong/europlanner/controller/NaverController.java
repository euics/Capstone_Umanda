package sejong.europlanner.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.service.serviceinterface.NaverService;
import sejong.europlanner.vo.response.user.ResponseNaverUser;

import java.io.IOException;

@RestController
@RequestMapping("/naver")
public class NaverController {

    private final NaverService naverService;

    @Autowired
    public NaverController(NaverService naverService) {
        this.naverService = naverService;
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseNaverUser> naverCallback(@RequestParam String code) throws Exception {
        JsonNode userProfile = naverService.getUserFromCode(code);
        ResponseNaverUser responseNaverUser = naverService.setToken(naverService.toResponse(userProfile));

        return ResponseEntity.ok().body(responseNaverUser);
    }

    @GetMapping("/user")
    public String naverGetUser(@RequestParam String code) throws IOException {
        JsonNode userProfile = naverService.getUserProfile(code);

        return userProfile.toString();
    }
}
