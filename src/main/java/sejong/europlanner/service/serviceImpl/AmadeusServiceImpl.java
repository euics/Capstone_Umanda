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
import sejong.europlanner.dto.FlightOfferDto;
import sejong.europlanner.dto.HotelListDto;
import sejong.europlanner.service.serviceinterface.AmadeusService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AmadeusServiceImpl implements AmadeusService {
    @Value("${amadeus.api_key}")
    private String apiKey;

    @Value("${amadeus.api_secret}")
    private String apiSecret;

    public String getAccessToken() {
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

    public List<HotelListDto> getHotelList(String cityCode) {
        String url = "https://test.api.amadeus.com/v1/reference-data/locations/hotels/by-city?cityCode=" + cityCode;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());

            List<HotelListDto> hotelInfoList = new ArrayList<>();
            for (JsonNode node : root.path("data")) {
                HotelListDto hotelInfo = new HotelListDto();
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

    public String getHotelInfo(List<String> hotelIds) {
        String hotelIdsString = String.join(",", hotelIds); // convert List<String> to comma-separated String
        String url = "https://test.api.amadeus.com/v2/e-reputation/hotel-sentiments?hotelIds=" + hotelIdsString;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    public List<FlightOfferDto> getFlightOffers(String originLocationCode,
                                                String destinationLocationCode,
                                                String departureDate,
                                                String adults) {
        String url = "https://test.api.amadeus.com/v2/shopping/flight-offers?nonStop=true&originLocationCode=" + originLocationCode
                + "&destinationLocationCode=" + destinationLocationCode + "&departureDate=" + departureDate + "&adults=" + adults;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());

            List<FlightOfferDto> flightOfferList = new ArrayList<>();
            for (JsonNode node : root.path("data")) {
                FlightOfferDto flightOffer = new FlightOfferDto();

                JsonNode itineraries = node.path("itineraries").get(0);
                JsonNode segments = itineraries.path("segments").get(0); // Assuming there's only one segment

                flightOffer.setDeparture(segments.path("departure").path("iataCode").asText());
                flightOffer.setArrival(segments.path("arrival").path("iataCode").asText());

                JsonNode price = node.path("price");
                flightOffer.setCurrency(price.path("currency").asText());
                flightOffer.setGrandTotal(price.path("grandTotal").asText());

                flightOfferList.add(flightOffer);
            }
            return flightOfferList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while extracting flight offers from Amadeus API response", e);
        }
    }
}