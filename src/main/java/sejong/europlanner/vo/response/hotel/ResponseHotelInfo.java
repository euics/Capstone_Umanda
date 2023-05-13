package sejong.europlanner.vo.response.hotel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseHotelInfo {
    private String name;
    private String hotelId;
    private double latitude;
    private double longitude;
}
