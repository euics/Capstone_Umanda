package sejong.europlanner.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sejong.europlanner.dto.HotelInfoDto;
import sejong.europlanner.service.serviceinterface.AmadeusService;
import sejong.europlanner.vo.response.hotel.ResponseHotelInfo;

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
    public ResponseEntity<List<ResponseHotelInfo>> getHotelList(@RequestParam String cityCode) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<HotelInfoDto> hotelList = amadeusService.getHotelList(cityCode);

        List<ResponseHotelInfo> responseHotelInfoList = new ArrayList<>();
        for(HotelInfoDto hid : hotelList){
            ResponseHotelInfo mappedResponse = mapper.map(hid, ResponseHotelInfo.class);
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
    public ResponseEntity<String> getFlightOffers(@RequestParam("originLocationCode") String originLocationCode,
                                                  @RequestParam("destinationLocationCode") String destinationLocationCode,
                                                  @RequestParam("departureDate") String departureDate,
                                                  @RequestParam("adults") String adults) {
        String flightOffers = amadeusService.getFlightOffers(originLocationCode, destinationLocationCode, departureDate, adults);
        return ResponseEntity.ok(flightOffers);
    }
}
