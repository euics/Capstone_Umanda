package sejong.europlanner.vo.response.amadues;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseHotelRate {
    private int numberOfRatings;
    private String hotelId;
    private int overallRating;
    private Map<String, Integer> sentiments;
}
