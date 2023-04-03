package sejong.europlanner.service.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sejong.europlanner.service.serviceinterface.NaverService;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

@Service
@Transactional
public class NaverServiceImpl implements NaverService {

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Value("${naver.redirect.uri}")
    private String redirectUri;

    public String getAccessToken(String code, String state) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("state", state);
        params.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                request,
                String.class
        );

        // 여기에서 JSON 응답을 구문 분석하여 액세스 토큰을 추출하십시오.
        // 예를 들어, Jackson 라이브러리를 사용하여 구문 분석할 수 있습니다.
        // 이 예에서는 응답 문자열을 반환합니다.
        return response.getBody();
    }

    public JsonNode getUserProfile(String accessToken) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.GET,
                request,
                String.class
        );

        // 응답 JSON 문자열을 JsonNode 객체로 변환합니다.
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(response.getBody());
    }

    public ResponseEntity<String> logout(String accessToken){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=" + clientId
                        + "&client_secret=" + clientSecret
                        + "&access_token=" + accessToken,
                HttpMethod.POST,
                request,
                String.class
        );
    }

    public JsonNode getUserFromCode(String code) throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        // 액세스 토큰 요청
        HttpHeaders tokenRequestHeaders = new HttpHeaders();
        tokenRequestHeaders.set("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
        tokenRequestHeaders.set("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        MultiValueMap<String, String> tokenRequestBody = new LinkedMultiValueMap<>();
        tokenRequestBody.add("grant_type", "authorization_code");
        tokenRequestBody.add("client_id", clientId);
        tokenRequestBody.add("client_secret", clientSecret);
        tokenRequestBody.add("redirect_uri", redirectUri);
        tokenRequestBody.add("code", code);

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenRequestBody, tokenRequestHeaders);

        ResponseEntity<String> response = restTemplate.postForEntity(
                "https://nid.naver.com/oauth2.0/token",
                tokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode accessTokenNode = objectMapper.readTree(response.getBody());

        // 사용자 프로필 정보 요청
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessTokenNode.get("access_token").asText());

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> userInfoResponse = restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.GET,
                request,
                String.class
        );

        return objectMapper.readTree(userInfoResponse.getBody());
    }
}
