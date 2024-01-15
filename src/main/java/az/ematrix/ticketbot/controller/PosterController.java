package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.PosterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/posters")
public class PosterController {

    private final PosterService eventsPictureService;

    @GetMapping("kids")
    public List<String> pictureOfKids() throws IOException {
        return PosterService.kidsPosterUrls();
    }

    @GetMapping("concerts")
    public List<String> pictureOfConcerts() throws IOException {
        return eventsPictureService.concertsPosterUrls();
    }

    @GetMapping("tourism")
    public List<String> pictureOfTourism() throws IOException {
        return eventsPictureService.tourismPosterUrls();
    }

    @GetMapping("search")
    public List<String> searchPicture(@RequestParam String search) throws IOException {
        return eventsPictureService.searchPicture(search);
    }
}
