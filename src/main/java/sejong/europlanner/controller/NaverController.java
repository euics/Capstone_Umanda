package sejong.europlanner.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sejong.europlanner.service.serviceinterface.NaverService;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/naver")
public class NaverController {

    private final NaverService naverService;

    @Autowired
    public NaverController(NaverService naverService) {
        this.naverService = naverService;
    }

    @GetMapping("/login")
    public String naverCallback(@RequestParam String code) throws Exception {
        JsonNode userProfile = naverService.getUserFromCode(code);

        return userProfile.toString();
    }

    @PostMapping("/user")
    public String naverGetUser(@RequestHeader("Authorization") String accessToken) throws IOException {
        JsonNode userProfile = naverService.getUserProfile(accessToken);
        return userProfile.toString();
    }

    @PostMapping("/logout")
    public HttpStatus naverLogout(@RequestHeader("Authorization") String accessToken) {
        ResponseEntity<String> response = naverService.logout(accessToken);

        return response.getStatusCode();
    }
}
