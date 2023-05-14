package sejong.europlanner.vo.response.amadues;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseFlightOffer {
    private String departure;
    private String arrival;
    private String currency;
    private String grandTotal;
}
