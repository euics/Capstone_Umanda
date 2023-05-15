package sejong.europlanner.dto;

import lombok.Data;

@Data
public class HotelListDto {
    private String name;
    private String hotelId;
    private double latitude;
    private double longitude;
}
