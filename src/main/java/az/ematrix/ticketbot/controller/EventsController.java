package az.ematrix.ticketbot.controller;

import az.ematrix.ticketbot.service.EventsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/events")
public class EventsController {
    private final EventsService eventsService;

    @GetMapping
    public ResponseEntity<String> events() throws IOException {
        return eventsService.events();
    }
    @GetMapping("/place/{eventPlace}")
    public ResponseEntity<String> eventsPlace(@PathVariable String eventPlace) throws IOException {
        return eventsService.processEvents(eventPlace);
    }

    @GetMapping("/ageLimit/{eventAgeLimit}")
    public ResponseEntity<String> ageLimit(@PathVariable String eventAgeLimit) throws IOException {
        return eventsService.ageLimit(eventAgeLimit);
    }

    @GetMapping("/where/{eventLocation}")
    public ResponseEntity<String> where(@PathVariable String eventLocation) throws IOException {
        return eventsService.where(eventLocation);
    }
}
