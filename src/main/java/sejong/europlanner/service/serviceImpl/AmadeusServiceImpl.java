package sejong.europlanner.service.serviceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sejong.europlanner.dto.HotelInfoDto;
import sejong.europlanner.vo.response.hotel.ResponseHotelInfo;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AmadeusServiceImpl {
    @Value("${amadeus.api_key}")
    private String apiKey;

    @Value("${amadeus.api_secret}")
    private String apiSecret;

    private String getAccessToken() {
        String url = "https://test.api.amadeus.com/v1/security/oauth2/token";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(apiKey, apiSecret);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        // JSON 응답에서 액세스 토큰을 추출합니다. 실제 구현에서는 JSON 라이브러리를 사용하여 응답을 처리하세요.
        String responseBody = response.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException("Error while extracting access token from Amadeus API response", e);
        }
    }

    public List<HotelInfoDto> getHotelList(String cityCode) {
        String url = "https://test.api.amadeus.com/v1/reference-data/locations/hotels/by-city?cityCode=" + cityCode;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());

            List<HotelInfoDto> hotelInfoList = new ArrayList<>();
            for (JsonNode node : root.path("data")) {
                HotelInfoDto hotelInfo = new HotelInfoDto();
                hotelInfo.setName(node.get("name").asText());
                hotelInfo.setHotelId(node.get("hotelId").asText());
                hotelInfo.setLatitude(node.get("geoCode").get("latitude").asDouble());
                hotelInfo.setLongitude(node.get("geoCode").get("longitude").asDouble());
                hotelInfoList.add(hotelInfo);
            }
            return hotelInfoList;
        } catch (Exception e) {
            throw new RuntimeException("Error while extracting hotel info from Amadeus API response", e);
        }
    }

    public String getHotelInfo(String hotelIds) {
        String url = "https://test.api.amadeus.com/v2/e-reputation/hotel-sentiments?hotelIds=" + hotelIds;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}