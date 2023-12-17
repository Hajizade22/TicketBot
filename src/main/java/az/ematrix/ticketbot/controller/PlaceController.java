package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("places")
    public String place() throws IOException {
        return placeService.places();
    }
    @GetMapping("map")
    public String map(int id)throws IOException{
        return placeService.map(id);
    }
    @GetMapping("phone")
    public String phone(int id)throws IOException{
        return placeService.phone(id);
    }

    @GetMapping("mobile")
    public String mobile(int id)throws IOException{
        return placeService.mobile(id);
    }




}
