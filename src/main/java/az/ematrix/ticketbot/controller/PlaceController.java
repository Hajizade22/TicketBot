package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.PlaceService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public ResponseEntity<String> place() throws IOException {
        return placeService.places();
    }

    @GetMapping("map/{id}")
    public ResponseEntity<String> map(@PathVariable Long id) throws IOException {
        return placeService.map(id);
    }

    @GetMapping("phone/{id}")
    public ResponseEntity<String> phone(@PathVariable Long id) throws IOException {
        return placeService.phone(id);
    }

    @GetMapping("mobile/{id}")
    public ResponseEntity<String> mobile(@PathVariable Long id) throws IOException {
        return placeService.mobile(id);
    }


}
