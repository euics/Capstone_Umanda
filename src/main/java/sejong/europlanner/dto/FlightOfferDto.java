package sejong.europlanner.dto;

import lombok.Data;

@Data
public class FlightOfferDto {
    private String departure;
    private String arrival;
    private String currency;
    private String grandTotal;
}
