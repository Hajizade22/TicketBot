package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.PosterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/posters")
public class PosterController {

    private final PosterService eventsPictureService;

    @GetMapping("kids")
    public ResponseEntity<?> pictureOfKids() {
        return ResponseEntity.ok(eventsPictureService.kidsPosterUrls());
    }

    @GetMapping("concerts")
    public ResponseEntity<?> pictureOfConcerts() {
        return ResponseEntity.ok(eventsPictureService.concertsPosterUrls());
    }

    @GetMapping("tourism")
    public ResponseEntity<?> pictureOfTourism() {
        return ResponseEntity.ok(eventsPictureService.tourismPosterUrls());
    }

    @GetMapping("/{search}")
    public ResponseEntity<?> searchPicture(@PathVariable String search) {
        return ResponseEntity.ok(eventsPictureService.searchPicture(search));
    }
}
