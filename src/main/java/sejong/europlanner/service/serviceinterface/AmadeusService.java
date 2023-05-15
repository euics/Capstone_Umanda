package sejong.europlanner.service.serviceinterface;

import sejong.europlanner.dto.FlightOfferDto;
import sejong.europlanner.dto.HotelListDto;
import sejong.europlanner.dto.HotelRateDto;

import java.util.List;

public interface AmadeusService {
    String getAccessToken();

    List<HotelListDto> getHotelList(String cityCode);

    List<HotelRateDto> getHotelRate(List<String> hotelIds);

    List<FlightOfferDto> getFlightOffers(String originLocationCode,
                                         String destinationLocationCode,
                                         String departureDate,
                                         String adults);
}
