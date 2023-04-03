package sejong.europlanner.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sejong.europlanner.service.serviceinterface.GoogleService;
import sejong.europlanner.vo.response.ResponseGoogleUser;

@RestController
@RequestMapping("/google")
public class GoogleController {
    private final GoogleService googleService;

    @Autowired
    public GoogleController(GoogleService googleService) {
        this.googleService = googleService;
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseGoogleUser> googleCallback(@RequestParam String code) throws Exception{
        JsonNode userProfile = googleService.getUserFromCode(code);
        ResponseGoogleUser responseGoogleUser = googleService.setToken(googleService.toResponse(userProfile));

        return ResponseEntity.ok().body(responseGoogleUser);
    }
}
