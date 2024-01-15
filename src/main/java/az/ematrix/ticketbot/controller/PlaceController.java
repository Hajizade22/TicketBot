package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.PlaceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("places")
    public String place() throws IOException {
        return placeService.places();
    }
    @GetMapping("map")
    public String map(@RequestParam int id)throws IOException{
        return placeService.map(id);
    }
    @GetMapping("phone")
    public String phone(@RequestParam int id)throws IOException{
        return placeService.phone(id);
    }

    @GetMapping("mobile")
    public String mobile(@RequestParam int id)throws IOException{
        return placeService.mobile(id);
    }




}
