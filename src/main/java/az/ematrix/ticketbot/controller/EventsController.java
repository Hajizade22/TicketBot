package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.EventsService;
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
@RequestMapping("/events")
public class EventsController {
    private final EventsService eventsService;

    @GetMapping("events")
    public String events() throws IOException {
        return eventsService.events();
    }
    @GetMapping("eventsPlace")
    public String eventsPlace (@RequestParam String place) throws IOException {
        return eventsService.processEvents(place);
    }
    @GetMapping("ageLimit")
    public String ageLimit (@RequestParam  String age) throws IOException {
        return eventsService.ageLimit(age);
    }
    @GetMapping("where")
    public String where (@RequestParam String place) throws IOException {
        return eventsService.where(place);
    }
}
