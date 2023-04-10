package sejong.europlanner.service.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sejong.europlanner.component.JwtTokenProvider;
import sejong.europlanner.service.serviceinterface.GoogleService;
import sejong.europlanner.vo.response.user.ResponseGoogleUser;

import javax.transaction.Transactional;

@Service
@Transactional
public class GoogleServiceImpl implements GoogleService {
    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public GoogleServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JsonNode getUserFromCode(String code) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // 액세스 토큰 요청
        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("client_id", clientId);
        tokenRequestBody.add("client_secret", clientSecret);
        tokenRequestBody.add("redirect_uri", redirectUri);
        tokenRequestBody.add("code", code);
        tokenRequestBody.add("grant_type", "authorization_code");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://oauth2.googleapis.com/token",
                tokenRequestBody,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode accessTokenNode = objectMapper.readTree(response.getBody());

        // 사용자 프로필 정보 요청
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessTokenNode.get("access_token").asText());

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> userInfoResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                request,
                String.class
        );

        return objectMapper.readTree(userInfoResponse.getBody());
    }

    public ResponseGoogleUser toResponse(JsonNode userProfile){
        String name = userProfile.get("name").asText();
        String picture = userProfile.get("picture").asText();

        ResponseGoogleUser responseGoogleUser = new ResponseGoogleUser();
        responseGoogleUser.setName(name);
        responseGoogleUser.setProfile(picture);

        return responseGoogleUser;
    }

    public ResponseGoogleUser setToken(ResponseGoogleUser responseGoogleUser){
        String jwtToken = jwtTokenProvider.generateToken(responseGoogleUser.getName());
        responseGoogleUser.setJwtToken(jwtToken);

        return responseGoogleUser;
    }
}
