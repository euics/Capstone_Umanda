package sejong.europlanner.dto;

import lombok.Data;

@Data
public class HotelInfoDto {
    private String name;
    private String hotelId;
    private double latitude;
    private double longitude;
}
