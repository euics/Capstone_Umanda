package sejong.europlanner.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sejong.europlanner.dto.FlightOfferDto;
import sejong.europlanner.dto.HotelListDto;
import sejong.europlanner.service.serviceinterface.AmadeusService;
import sejong.europlanner.vo.response.amadues.ResponseFlightOffer;
import sejong.europlanner.vo.response.amadues.ResponseHotelList;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Authorization")
public class AmadeusController {
    private final AmadeusService amadeusService;

    @Autowired
    public AmadeusController(AmadeusService amadeusService) {
        this.amadeusService = amadeusService;
    }

    @GetMapping("/hotel/list")
    public ResponseEntity<List<ResponseHotelList>> getHotelList(@RequestParam String cityCode) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<HotelListDto> hotelList = amadeusService.getHotelList(cityCode);

        List<ResponseHotelList> responseHotelInfoList = new ArrayList<>();
        for(HotelListDto hid : hotelList){
            ResponseHotelList mappedResponse = mapper.map(hid, ResponseHotelList.class);
            responseHotelInfoList.add(mappedResponse);
        }

        return ResponseEntity.ok(responseHotelInfoList);
    }

    @GetMapping("/hotel/info")
    public ResponseEntity<String> getHotelInfo(@RequestParam List<String> hotelIds) {
        String hotelList = amadeusService.getHotelInfo(hotelIds);
        return ResponseEntity.ok(hotelList);
    }

    @GetMapping("/airplane/info")
    public ResponseEntity<List<ResponseFlightOffer>> getFlightOffers(@RequestParam String originLocationCode,
                                                  @RequestParam String destinationLocationCode,
                                                  @RequestParam String departureDate,
                                                  @RequestParam String adults) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<ResponseFlightOffer> responseFlightOfferList = new ArrayList<>();
        List<FlightOfferDto> flightOffers = amadeusService.getFlightOffers(originLocationCode, destinationLocationCode, departureDate, adults);

        for(FlightOfferDto fod : flightOffers){
            ResponseFlightOffer mappedResponse = mapper.map(fod, ResponseFlightOffer.class);
            responseFlightOfferList.add(mappedResponse);
        }

        return ResponseEntity.ok(responseFlightOfferList);
    }
}
