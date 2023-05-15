package sejong.europlanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class HotelRateDto {
    private int numberOfRatings;
    private String hotelId;
    private int overallRating;
    private Map<String, Integer> sentiments;
}
