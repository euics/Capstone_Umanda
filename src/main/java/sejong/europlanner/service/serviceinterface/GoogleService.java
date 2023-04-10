package sejong.europlanner.service.serviceinterface;

import com.fasterxml.jackson.databind.JsonNode;
import sejong.europlanner.vo.response.user.ResponseGoogleUser;

public interface GoogleService {
    JsonNode getUserFromCode(String code) throws Exception;

    ResponseGoogleUser toResponse(JsonNode userProfile);

    ResponseGoogleUser setToken(ResponseGoogleUser responseGoogleUser);
}
